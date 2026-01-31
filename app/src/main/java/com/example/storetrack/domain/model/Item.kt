package com.example.storetrack.domain.model

data class Item(
    val id: String,
    val name: String,
    val description: String,
    val stock: Int,
    val price: Int,
    val photo: String?
)