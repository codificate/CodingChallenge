package com.globallogic.codingchallenge.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemData")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val description: String,
    val image: String,
    val title: String
)