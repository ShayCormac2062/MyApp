package com.example.myapp.ui.screen.list

import com.example.myapp.domain.model.Item

sealed class ListState {

    object Loading : ListState()
    data class Error(
        val error: String,
        val onEventListener: () -> Unit
    ) : ListState()

    object EmptyList : ListState()

    data class Success(val item: List<Item>?) : ListState()

}
