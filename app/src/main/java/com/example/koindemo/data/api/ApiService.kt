package com.example.koindemo.data.api

import com.example.koindemo.data.api.model.Post
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type:application/json")
    @GET("/posts")
    suspend fun getPost(): ApiResponse<Post>
}