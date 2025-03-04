package com.example.fetchapp.data.repository

import com.example.fetchapp.data.api.ApiService
import com.example.fetchapp.data.data.Item
import javax.inject.Inject

class HiringRepositoryImpl @Inject constructor(private val apiService: ApiService) : HiringRepository{
    override suspend fun getItems(): List<Item> {
       val response = apiService.getItems()
        return if(response.isSuccessful) response.body()?: emptyList() else emptyList()
    }

}