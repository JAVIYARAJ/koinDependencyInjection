package com.example.koindemo.data.api.repository

import com.example.koindemo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getPost() = apiHelper.getPosts()
}
