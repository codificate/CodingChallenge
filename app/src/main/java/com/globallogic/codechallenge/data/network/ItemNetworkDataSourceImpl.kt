package com.globallogic.codechallenge.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.globallogic.codechallenge.data.network.response.ItemData
import com.globallogic.codechallenge.data.network.response.ItemsReponse
import com.globallogic.codechallenge.internal.NoConnectivityException

class ItemNetworkDataSourceImpl(
    private val codeChallengeApiService : CodeChallengeApiService
) : ItemNetworkDataSource {

    private val _downloadedItems = MutableLiveData<List<ItemData>>()
    override val downloadedItems: LiveData<List<ItemData>>
        get() = _downloadedItems

    override suspend fun fetchItems() {
        try {
            val fetchItems = codeChallengeApiService
                .getItems()
                .await()
            _downloadedItems.postValue(fetchItems)
        }
        catch (e: NoConnectivityException) {
            Log.e("ItemNetworkDataSource", e.message)
        }
    }
}
