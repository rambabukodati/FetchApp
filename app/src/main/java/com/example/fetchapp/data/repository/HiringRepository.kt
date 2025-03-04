package com.example.fetchapp.data.repository

import com.example.fetchapp.data.data.Item

interface HiringRepository {
    suspend fun getItems():List<Item>
}