package com.example.storetrack.domain.usecase

import com.example.storetrack.domain.repository.ItemFirestoreRepository

class DeleteItemUseCase(private val itemFirestoreRepository: ItemFirestoreRepository) {
    suspend operator fun invoke(id: String): Boolean {
        return itemFirestoreRepository.delete(id)
    }
}