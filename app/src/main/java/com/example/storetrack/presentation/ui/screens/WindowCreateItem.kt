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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storetrack.presentation.viewmodel.CreateItemViewModel


@Composable
fun WindowCreateItem(
    navController: NavController,
    windowItemViewModel: CreateItemViewModel = viewModel()
) {
    val item by windowItemViewModel.item.collectAsState()

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
                value = item.description,
                label = { Text("Precio", color = Color.DarkGray) },
                onValueChange = {
                    windowItemViewModel.setDescription(it)
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
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Guardar")
                }
            }


        }
    }
}