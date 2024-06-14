package com.example.quoteofthedayapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteofthedayapp.data.QuoteData
import com.example.quoteofthedayapp.repository.QuoteRepository
import com.example.quoteofthedayapp.utils.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val quoteRepository: QuoteRepository):ViewModel() {
    private val _homeScreenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val homeScreenState:StateFlow<HomeScreenState> = _homeScreenState

    private val _listOfQuote=MutableStateFlow<List<QuoteData>>(emptyList())
    val listOfQuote:StateFlow<List<QuoteData>> = _listOfQuote

    private val _isInDB = MutableStateFlow<Boolean>(false)
    val isInDB:StateFlow<Boolean> = _isInDB
    init {
        getDailyQuotes()
    }
     private fun getDailyQuotes(){
        viewModelScope.launch {
          try {
              val response=quoteRepository.getRandomDailyQuote()
              if (response != null) {
                  _isInDB.value= quoteRepository.isInLocalDB(response.id)
              }
              _homeScreenState.value=HomeScreenState.Success(response)
          }catch (e:Exception){
              _homeScreenState.value=HomeScreenState.Error(e.message.toString())
          }
        }
    }

    fun addToFavouriteList(quote: QuoteData){
        viewModelScope.launch {
            try {
                quoteRepository.addFavouriteQuote(quote)
            }catch (e:Exception){
                Log.d("add to Fav", "addToFavouriteList: ${e.message}")
            }
        }
    }
    fun change(){

        _isInDB.value=!_isInDB.value
    }
    fun getFavouriteList(){
        viewModelScope.launch {
            try {
               val res= quoteRepository.getFavouriteQuotes()

                _listOfQuote.value=res
                Log.d("get to Fav", "getFavouriteList: $res")
            }catch (e:Exception){
                Log.d("get to Fav", "getFavouriteList: ${e.message}")
            }
        }
    }

    fun removeFromFavourite(quote: QuoteData){
        viewModelScope.launch {
            quoteRepository.removeFavouriteQuote(quote)
        }
    }

    fun deleteAllFavourite(){
        viewModelScope.launch {
            quoteRepository.deleteAllFavouriteQuote()
        }
    }
}