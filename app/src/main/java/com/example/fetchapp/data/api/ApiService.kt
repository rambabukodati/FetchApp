package com.example.fetchapp.data.api

import com.example.fetchapp.data.data.Item
import com.example.fetchapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.HIRING_END_POINT)
    suspend fun getItems(): Response<List<Item>>
}