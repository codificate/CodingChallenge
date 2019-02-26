package com.globallogic.codechallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globallogic.codechallenge.data.db.entity.ItemEntry

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkItemsInsert(item: List<ItemEntry>)

    @Query("select * from itemData")
    fun getAll(): LiveData<List<ItemEntry>>
}