package com.globallogic.codechallenge.data.repository

import androidx.lifecycle.LiveData
import com.globallogic.codechallenge.data.db.entity.ItemEntry

interface CodingChallengeRepository {
    suspend fun getAllItemsFromDb(): LiveData<List<ItemEntry>>
    suspend fun fetchAllItems(): LiveData<List<ItemEntry>>
}