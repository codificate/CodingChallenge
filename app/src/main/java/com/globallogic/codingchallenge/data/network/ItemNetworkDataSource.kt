package com.globallogic.codingchallenge.data.network

import androidx.lifecycle.LiveData
import com.globallogic.codingchallenge.data.network.response.ItemsReponse

interface ItemNetworkDataSource {
    val downloadedItems: LiveData<ItemsReponse>

    suspend fun fetchItems()
}