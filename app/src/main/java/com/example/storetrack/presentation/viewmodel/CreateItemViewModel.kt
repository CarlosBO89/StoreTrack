package com.example.storetrack.presentation.viewmodel

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.storetrack.domain.model.Item
import com.example.storetrack.domain.usecase.SaveItemUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateItemViewModel(val saveItemUseCase: SaveItemUseCase) : ViewModel() {

    private val _item = MutableStateFlow(
        Item("0", "", "", 0, 0, "")
    )

    val item: StateFlow<Item> = _item

    fun setItem(item: Item) {
        _item.value = item
    }

    fun setName(name: String) {
        _item.value = _item.value.copy(name = name)
    }
    fun setPhoto(photoName: String) {
        _item.value = _item.value.copy(photo = photoName)
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

    fun save(navController: NavController, snackbarHostState: SnackbarHostState) {
        viewModelScope.launch {
            if (saveItemUseCase(item.value)) {
                navController.popBackStack()
            } else {
                snackbarHostState.showSnackbar("Error al guardar el item")
            }
        }
    }
}