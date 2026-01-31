package com.example.storetrack.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import com.example.storetrack.domain.model.Item
import kotlinx.coroutines.flow.StateFlow

class CreateItemViewModel : ViewModel() {

    private val _item = MutableStateFlow(
        Item("0", "", "", 0, 0, null)
    )

    val item: StateFlow<Item> = _item


    fun setName(name: String) {
        _item.value = _item.value.copy(name = name)
    }

    fun setPhoto(photo: String) {
        _item.value = _item.value.copy(photo = photo)
    }

    fun setDescription(description: String) {
        _item.value = _item.value.copy(description = description)
    }

    fun setStock(stock: Int) {
        _item.value = _item.value.copy(stock = stock)
    }

    fun setPrice(price: Int) {
        _item.value = _item.value.copy(price = price)
    }
}