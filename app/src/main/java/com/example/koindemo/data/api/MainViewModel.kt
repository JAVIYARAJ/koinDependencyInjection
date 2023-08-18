package com.example.koindemo.data.api

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koindemo.App
import com.example.koindemo.data.api.model.Post
import com.example.koindemo.data.api.repository.MainRepository
import com.example.koindemo.di.module.viewModelModule
import com.example.koindemo.utils.Resource
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.*
import retrofit2.http.POST
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val _postList = MutableLiveData<Post>()
    var postList: LiveData<Post> = _postList

    fun getPost() {
        launch {
            val result = withContext(Dispatchers.IO) {
                mainRepository.getPost()
            }

            when (result) {
                is ApiResponse.Success -> {
                    _postList.postValue(result.data)
                }

                is ApiResponse.Failure.Error -> {
                    Toast.makeText(
                        App.mInstance.applicationContext,
                        "something went wrong.please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                }
            }
        }

    }

}