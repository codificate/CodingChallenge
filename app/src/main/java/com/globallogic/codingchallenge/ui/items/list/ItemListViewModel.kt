package com.globallogic.codingchallenge.ui.items.list

import androidx.lifecycle.ViewModel
import com.globallogic.codingchallenge.data.repository.CodingChallengeRepository
import com.globallogic.codingchallenge.internal.lazyDeferred

class ItemListViewModel(
    private val codeChallengeRepository: CodingChallengeRepository
) : ViewModel() {

    val itemList by lazyDeferred {
        codeChallengeRepository.getAllItems()
    }
}