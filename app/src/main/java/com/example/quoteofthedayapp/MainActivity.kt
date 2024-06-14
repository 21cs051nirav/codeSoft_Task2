package com.example.quoteofthedayapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            QuoteDataBase::class.java,
            "favorite_quote.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    private val quoteViewModel by viewModels<QuoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return QuoteViewModel(QuoteRepository(RetrofitInstance.api, db.quoteDao())) as T
                }
            }
        }
    )
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        run {
            if (isGranted) {

            } else {

            }
        }
    }

    private fun requestStoragePermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("kilo", "permissions dialog")

            else -> requestPermissionLauncher.launch(STORAGE_SERVICE)
        }

    }

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteOfTheDayAppTheme {
                val navController = rememberNavController()
                val permissionStatus = rememberPermissionState(permission = STORAGE_SERVICE)
                if (!permissionStatus.status.isGranted) {
                    requestStoragePermission()
                }


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->

                    NavigationHost(
                        navController = navController,
                        quoteViewModel = quoteViewModel,
                        applicationContext,
                        innerPadding
                    )

                }

            }
        }
    }
}

//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun StoragePermissionExample() {
//    val storagePermissionState = rememberPermissionState(permission =Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        when (storagePermissionState.status) {
//            PermissionStatus.Granted -> {
//                Text(text = "Storage permission granted")
//                // You can add your logic that requires storage permission here
//            }
//            is PermissionStatus.Denied -> {
//                Column {
//                    val textToShow = if ((storagePermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
//                        "Storage permission is needed to save and share images. Please grant the permission."
//                    } else {
//                        "Storage permission required to save and share images. Please grant the permission."
//                    }
//                    Text(text = textToShow)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Button(onClick = { storagePermissionState.launchPermissionRequest() }) {
//                        Text("Request Permission")
//                    }
//                }
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuoteOfTheDayAppTheme {

    }
}