package com.example.storetrack.domain.usecase

import com.example.storetrack.domain.model.Item
import com.example.storetrack.domain.repository.ItemFirestoreRepository
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(private val itemFirestoreRepository: ItemFirestoreRepository) {
    operator fun invoke(): Flow<List<Item>> {
        return itemFirestoreRepository.list()
    }
}