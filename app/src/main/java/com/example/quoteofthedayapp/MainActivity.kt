package com.example.quoteofthedayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.quoteofthedayapp.component.BottomNavigationBar
import com.example.quoteofthedayapp.component.NavigationHost
import com.example.quoteofthedayapp.data.api.RetrofitInstance
import com.example.quoteofthedayapp.data.local.QuoteDataBase
import com.example.quoteofthedayapp.repository.QuoteRepository
import com.example.quoteofthedayapp.ui.theme.QuoteOfTheDayAppTheme
import com.example.quoteofthedayapp.viewModel.QuoteViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            QuoteDataBase::class.java,
            "favorite_quote.db"
        ).build()
    }
    private val quoteViewModel by viewModels<QuoteViewModel>(
        factoryProducer = {
            object:ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return QuoteViewModel(QuoteRepository(RetrofitInstance.api,db.quoteDao())) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteOfTheDayAppTheme {
                val navController= rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->

                    NavigationHost(navController = navController  , quoteViewModel =quoteViewModel ,applicationContext,innerPadding)

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuoteOfTheDayAppTheme {

    }
}