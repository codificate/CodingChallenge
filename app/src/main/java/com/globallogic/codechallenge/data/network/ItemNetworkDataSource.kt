package com.globallogic.codechallenge.data.network

import androidx.lifecycle.LiveData
import com.globallogic.codechallenge.data.network.response.ItemData
import com.globallogic.codechallenge.data.network.response.ItemsReponse

interface ItemNetworkDataSource {
    val downloadedItems: LiveData<List<ItemData>>

    suspend fun fetchItems()
}