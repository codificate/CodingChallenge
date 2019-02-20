package com.globallogic.codingchallenge.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.globallogic.codingchallenge.data.network.response.ItemsReponse
import com.globallogic.codingchallenge.internal.NoConnectivityException

class ItemNetworkDataSourceImpl(
    private val codeChallengeApiService : CodeChallengeApiService
) : ItemNetworkDataSource {
    private val _downloadedItems = MutableLiveData<ItemsReponse>()
    override val downloadedItems: LiveData<ItemsReponse>
        get() = _downloadedItems

    override suspend fun fetchItems() {
        try {
            val fetchItems = codeChallengeApiService
                .getItems()
                .await()
            _downloadedItems.postValue(fetchItems)
        }
        catch (e: NoConnectivityException) {}
    }
}
