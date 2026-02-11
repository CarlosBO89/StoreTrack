package com.example.storetrack.domain.model

import com.google.firebase.firestore.DocumentId

data class Item(
    @DocumentId
    val id: String = "",
    val name: String,
    val description: String,
    val stock: Int,
    val price: Int,
    val photo: String?
) {
    constructor() : this(name = "", description = "", stock = 0,price = 0,photo = "")
}