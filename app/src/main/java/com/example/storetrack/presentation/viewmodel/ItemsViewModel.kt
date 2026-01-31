package com.example.storetrack.presentation.viewmodel

import com.example.storetrack.domain.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {
    private val _items = MutableStateFlow(
        listOf(
            Item(
                "1",
                "Item 1",
                "Esto es un Item",
                0,
                1,
                "android.resource://com.example.storetrack/drawable/itemiamge"
            ),
            Item(
                "2",
                "Item 2",
                "Esto es un Item",
                0,
                1,
                "android.resource://com.example.storetrack/drawable/itemiamge"
            ),
            Item(
                "3",
                "Item 3",
                "Esto es un Item",
                0,
                1,
                "android.resource://com.example.storetrack/drawable/itemiamge"
            ),
            Item(
                "4",
                "Item 4",
                "Esto es un Item",
                0,
                1,
                "android.resource://com.example.storetrack/drawable/itemiamge"
            ),
            Item(
                "5",
                "Item 5",
                "Esto es un Item",
                0,
                1,
                "android.resource://com.example.storetrack/drawable/itemiamge"
            )
        )
    )
    val items: StateFlow<List<Item>> = _items
    fun removeItem(id: String) {
        _items.value = _items.value.filter { it.id != id }
    }
}