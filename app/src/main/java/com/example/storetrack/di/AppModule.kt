package com.example.storetrack.di

import com.example.storetrack.domain.repository.ItemFirestoreRepository
import com.example.storetrack.domain.usecase.DeleteItemUseCase
import com.example.storetrack.domain.usecase.GetItemsUseCase
import com.example.storetrack.domain.usecase.SaveItemUseCase
import com.example.storetrack.presentation.viewmodel.CreateItemViewModel
import com.example.storetrack.presentation.viewmodel.ItemsViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseFirestore.getInstance() }
    single { ItemFirestoreRepository(get()) }
    factory { GetItemsUseCase(get()) }
    factory { DeleteItemUseCase(get()) }
    factory { SaveItemUseCase(get()) }
    viewModel { ItemsViewModel(get(), get()) }
    viewModel { CreateItemViewModel(get()) }
}