package com.example.storetrack.domain.repository

import com.example.storetrack.domain.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ItemFirestoreRepository(val firestore: FirebaseFirestore) {

    private val itemsCollection = firestore.collection("items")

    suspend fun getById(id: String): Item? {
        return try {
            val documentSnapshot = itemsCollection.document(id).get().await()
            documentSnapshot.toObject(Item::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun list(): Flow<List<Item>> {
        return queryForList(
            itemsCollection,
            Item::class.java
        )
    }

    suspend fun save(item: Item): Boolean {
        return try {
            itemsCollection.add(item).await()
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

    private fun <T> queryForList(query: Query, clazz: Class<T>): Flow<List<T>> {
        return callbackFlow {

            val listener = query
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val items = snapshots?.documents?.mapNotNull { doc ->
                        doc.toObject(clazz)

                    } ?: emptyList()

                    trySend(items)
                }

            awaitClose() { listener.remove() }
        }
    }

    private fun <T> queryForSingle(query: Query, clazz: Class<T>): Flow<T?> {
        return callbackFlow {
            val listener = query
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val item = snapshots?.documents?.firstOrNull()?.toObject(clazz)

                    trySend(item)
                }
            awaitClose() { listener.remove() }
        }
    }
}