package com.example.storetrack.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storetrack.presentation.viewmodel.CreateItemViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun WindowCreateItem(
    navController: NavController,
    windowItemViewModel: CreateItemViewModel = koinViewModel()
) {
    val item by windowItemViewModel.item.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(24.dp)
        ) {

            Text(
                "Crear Item",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre", color = Color.DarkGray) },
                value = item.name,
                onValueChange = {
                    windowItemViewModel.setName(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = item.description,
                label = { Text("Descripción", color = Color.DarkGray) },
                onValueChange = {
                    windowItemViewModel.setDescription(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = item.price.toString(),
                label = { Text("Precio", color = Color.DarkGray) },
                onValueChange = {
                    it.toIntOrNull()?.let { price ->
                        windowItemViewModel.setPrice(price)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = item.photo ?: "",
                label = { Text("Foto", color = Color.DarkGray) },
                onValueChange = {
                    windowItemViewModel.setPhoto(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Cancelar")
                }
                Button(
                    onClick = { windowItemViewModel.save(navController, snackbarHostState) }
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}