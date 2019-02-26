package com.globallogic.codechallenge.ui.items.list

import androidx.lifecycle.ViewModel
import com.globallogic.codechallenge.data.repository.CodingChallengeRepository
import com.globallogic.codechallenge.internal.lazyDeferred

class ItemListViewModel(
    private val codeChallengeRepository: CodingChallengeRepository
) : ViewModel() {

    /*val itemList by lazyDeferred {
        codeChallengeRepository.getAllItemsFromDb()
    }*/

    val fetchListItems by lazyDeferred {
        codeChallengeRepository.fetchAllItems()
    }
}