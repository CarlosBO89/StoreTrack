package com.example.storetrack.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storetrack.presentation.viewmodel.ItemsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    id: String?,
    itemsViewModel: ItemsViewModel = koinViewModel()
) {
    val items by itemsViewModel.items.collectAsState()
    val myItem = remember(id, items) {
        items.find { it.id == id }
    }
    Scaffold { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text(
                text = "Details for Item : $id",
                style = MaterialTheme.typography.headlineMedium,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Id : ${myItem?.id}",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Nombre : ${myItem?.name}",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Descripción : ${myItem?.description}",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Stock : ${myItem?.stock}",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Url Imagen : ${myItem?.photo}",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text("Go Back")
            }
        }
    }
}