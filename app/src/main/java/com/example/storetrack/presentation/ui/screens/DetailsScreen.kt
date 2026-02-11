package com.example.storetrack.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storetrack.presentation.viewmodel.CreateItemViewModel
import com.example.storetrack.presentation.viewmodel.ItemsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    id: String?,
    itemsViewModel: ItemsViewModel = koinViewModel(),
    createItemViewModel: CreateItemViewModel = koinViewModel()
) {
    val items by itemsViewModel.items.collectAsState()
    val itemToEdit by createItemViewModel.item.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(id, items) {
        items.find { it.id == id }?.let {
            createItemViewModel.setItem(it)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text(
                text = "Edit Item : $id",
                style = MaterialTheme.typography.headlineMedium,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre", color = Color.DarkGray) },
                value = itemToEdit.name,
                onValueChange = { createItemViewModel.setName(it) }
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descripción", color = Color.DarkGray) },
                value = itemToEdit.description,
                onValueChange = { createItemViewModel.setDescription(it) }
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Stock", color = Color.DarkGray) },
                value = itemToEdit.stock.toString(),
                onValueChange = { it.toIntOrNull()?.let { stock -> createItemViewModel.setStock(stock) } }
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Precio", color = Color.DarkGray) },
                value = itemToEdit.price.toString(),
                onValueChange = { it.toIntOrNull()?.let { price -> createItemViewModel.setPrice(price) } }
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Url Imagen", color = Color.DarkGray) },
                value = itemToEdit.photo ?: "",
                onValueChange = { createItemViewModel.setPhoto(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
                Button(onClick = { createItemViewModel.save(navController, snackbarHostState) }) {
                    Text("Save Changes")
                }
            }
        }
    }
}