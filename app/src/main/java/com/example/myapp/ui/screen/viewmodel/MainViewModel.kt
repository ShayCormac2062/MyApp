package com.example.myapp.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.usecase.GetItemByIdUseCase
import com.example.myapp.domain.usecase.GetItemsBySearchUseCase
import com.example.myapp.domain.usecase.GetItemsUseCase
import com.example.myapp.ui.screen.item.ItemState
import com.example.myapp.ui.screen.list.ListState
import com.example.myapp.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val getItemsBySearchUseCase: GetItemsBySearchUseCase
) : ViewModel() {

    private var _listState: MutableStateFlow<ListState> =
        MutableStateFlow(ListState.Loading)
    val listState: StateFlow<ListState> = _listState

    private var _itemState: MutableStateFlow<ItemState> =
        MutableStateFlow(ItemState.Loading)
    val itemState: StateFlow<ItemState> = _itemState

    init {
        getItems()
    }

    fun getItems() {
        _listState.value = ListState.Loading
        viewModelScope.launch {
            _listState.value = when(val state = getItemsUseCase()) {
                is RequestResult.Error -> {
                    if (state.data == null) {
                        ListState.Error(state.exception?.message.toString()) {
                            getItems()
                        }
                    } else ListState.EmptyList
                }
                is RequestResult.Success -> ListState.Success(state.data)
            }
        }
    }

    fun getItemsBySearch(search: String) {
        _listState.value = ListState.Loading
        viewModelScope.launch {
            _listState.value = when(val state = getItemsBySearchUseCase(search)) {
                is RequestResult.Error -> {
                    if (state.data == null) {
                        ListState.Error(state.exception?.message.toString()) {
                            getItemsBySearch(search)
                        }
                    } else ListState.EmptyList
                }
                is RequestResult.Success -> ListState.Success(state.data)
            }
        }
    }

    fun getItem(id: Int) {
        _itemState.value = ItemState.Loading
        viewModelScope.launch {
            _itemState.value = when(val state = getItemByIdUseCase(id)) {
                is RequestResult.Error -> ItemState.Error(state.exception?.message.toString()) {
                    getItem(id)
                }
                is RequestResult.Success -> ItemState.Success(state.data)
            }
        }
    }
}

