package com.example.storetrack.domain.usecase

import com.example.storetrack.domain.model.Item
import com.example.storetrack.domain.repository.ItemFirestoreRepository

class SaveItemUseCase (private val itemFirestoreRepository: ItemFirestoreRepository) {
    suspend operator fun invoke(item: Item): Boolean {
        return itemFirestoreRepository.save(item)
    }
}