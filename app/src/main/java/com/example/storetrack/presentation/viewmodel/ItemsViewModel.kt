package com.example.storetrack.presentation.viewmodel

import com.example.storetrack.domain.model.Item
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storetrack.domain.usecase.DeleteItemUseCase
import com.example.storetrack.domain.usecase.GetItemsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val getItemsUseCase: GetItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {
    private var _items = getItemsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val items: StateFlow<List<Item>> = _items
    fun removeItem(id: String) {
        viewModelScope.launch {
            deleteItemUseCase(id)
        }
    }
}