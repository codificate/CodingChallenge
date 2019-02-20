package com.globallogic.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import com.globallogic.codingchallenge.data.db.ItemDao
import com.globallogic.codingchallenge.data.db.entity.Item
import com.globallogic.codingchallenge.data.network.ItemNetworkDataSource
import com.globallogic.codingchallenge.data.network.response.ItemsReponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CodingChallengeRepositoryImpl(
    private val itemDao: ItemDao,
    private val itemNetworkDataSource: ItemNetworkDataSource
) : CodingChallengeRepository {

    init {
        itemNetworkDataSource.apply {
            downloadedItems.observeForever { response ->
                persistFetchedItems(response)
            }
        }
    }

    override suspend fun getAllItems(): LiveData<List<Item>> {
        return withContext(Dispatchers.IO) {
            return@withContext itemDao.getAll()
        }
    }

    private fun persistFetchedItems(itemsReponse: ItemsReponse){
        GlobalScope.launch(Dispatchers.IO) {
            itemDao.bulkItemsInsert(itemsReponse.itemData)
        }
    }

}