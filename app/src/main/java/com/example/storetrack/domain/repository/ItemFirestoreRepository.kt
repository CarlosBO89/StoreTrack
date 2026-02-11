package com.example.storetrack.domain.repository

import com.example.storetrack.domain.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ItemFirestoreRepository(val firestore: FirebaseFirestore) {

    private val itemsCollection = firestore.collection("items")

    suspend fun getById(id: String): Item? {
        return try {
            val documentSnapshot = itemsCollection.document(id).get().await()
            documentSnapshot.toObject(Item::class.java)?.copy(id = documentSnapshot.id)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun list(): Flow<List<Item>> {
        return callbackFlow {
            val listener = itemsCollection.addSnapshotListener { snapshots, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val items = snapshots?.documents?.mapNotNull { doc ->
                    doc.toObject(Item::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                trySend(items)
            }

            awaitClose { listener.remove() }
        }
    }

    suspend fun save(item: Item): Boolean {
        return try {
            if (item.id.isEmpty() || item.id == "0") {
                itemsCollection.add(item).await()
            } else {
                itemsCollection.document(item.id).set(item).await()
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun delete(id: String): Boolean {
        return try {
            itemsCollection.document(id).delete().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}