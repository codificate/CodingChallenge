package com.globallogic.codechallenge.data.repository

import androidx.lifecycle.LiveData
import com.globallogic.codechallenge.data.db.ItemDao
import com.globallogic.codechallenge.data.db.entity.ItemEntry
import com.globallogic.codechallenge.data.network.ItemNetworkDataSource
import com.globallogic.codechallenge.data.network.response.ItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CodingChallengeRepositoryImpl(
    private val itemDao: ItemDao,
    private val itemNetworkDataSource: ItemNetworkDataSource
) : CodingChallengeRepository {

    override suspend fun fetchAllItems(): LiveData<List<ItemEntry>> {
        return withContext(Dispatchers.IO) {
            initFetch()
            return@withContext getAllItemsFromDb()
        }
    }

    init {
        itemNetworkDataSource.apply {
            downloadedItems.observeForever { response ->
                persistFetchedItems(response)
            }
        }
    }

    private suspend fun initFetch(){
        itemNetworkDataSource.fetchItems()
    }

    override suspend fun getAllItemsFromDb(): LiveData<List<ItemEntry>> {
        return withContext(Dispatchers.IO) {
            return@withContext itemDao.getAll()
        }
    }

    private fun persistFetchedItems(itemsReponse: List<ItemData>){
        GlobalScope.launch(Dispatchers.IO) {
            itemDao
                .bulkItemsInsert(
                    itemsReponse
                        .map { itemData -> ItemEntry(null, itemData.description, itemData.image, itemData.title) })
        }
    }

}