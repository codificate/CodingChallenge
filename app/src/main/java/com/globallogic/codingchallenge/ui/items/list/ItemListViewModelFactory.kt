package com.globallogic.codingchallenge.ui.items.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.globallogic.codingchallenge.data.repository.CodingChallengeRepository

class ItemListViewModelFactory(
    private val codeChallengeRepository: CodingChallengeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemListViewModel(codeChallengeRepository) as T
    }
}