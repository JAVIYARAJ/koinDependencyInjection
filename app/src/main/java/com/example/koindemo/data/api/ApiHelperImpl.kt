package com.example.koindemo.data.api

import com.example.koindemo.data.api.model.Post
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPosts(): ApiResponse<Post> {
        val response=apiService.getPost().suspendOnSuccess {
            return@suspendOnSuccess
        }.suspendOnError {
            return@suspendOnError
        }.suspendOnException {
            return@suspendOnException
        }

        return response
    }
}
