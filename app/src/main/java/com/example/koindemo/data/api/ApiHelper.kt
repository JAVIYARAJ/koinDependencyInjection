package com.example.koindemo.data.api

import com.example.koindemo.data.api.model.Post
import com.skydoves.sandwich.ApiResponse

interface ApiHelper {
    suspend fun getPosts(): ApiResponse<Post>
}