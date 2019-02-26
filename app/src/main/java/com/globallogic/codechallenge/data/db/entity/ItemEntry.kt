package com.globallogic.codechallenge.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "itemData", indices = arrayOf(Index(value = ["title"], unique = true)))
data class ItemEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val description: String,
    val image: String,
    val title: String
)