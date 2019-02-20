package com.globallogic.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import com.globallogic.codingchallenge.data.db.entity.Item

interface CodingChallengeRepository {
    suspend fun getAllItems(): LiveData<List<Item>>
}