package com.example.myapp.ui.screen.item

import com.example.myapp.domain.model.Item

sealed class ItemState {

    object Loading : ItemState()
    data class Error(
        val error: String,
        val onEventListener: () -> Unit
    ) : ItemState()

    data class Success(val item: Item?) : ItemState()

}
