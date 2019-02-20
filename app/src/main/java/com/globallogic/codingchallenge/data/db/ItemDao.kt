package com.globallogic.codingchallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globallogic.codingchallenge.data.db.entity.Item
import com.globallogic.codingchallenge.data.network.response.ItemData

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkItemsInsert(item: List<ItemData>)

    @Query("select * from itemData")
    fun getAll(): LiveData<List<Item>>
}