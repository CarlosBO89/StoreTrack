package com.example.storetrack.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.storetrack.R
import com.example.storetrack.domain.model.Item
import com.example.storetrack.presentation.navigation.Screen
import com.example.storetrack.presentation.ui.components.ActionMenu
import com.example.storetrack.presentation.viewmodel.ItemsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ItemsScreen(navController: NavController, itemsViewModel: ItemsViewModel = koinViewModel ()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val items by itemsViewModel.items.collectAsState()

    Scaffold(
        topBar = {
            ActionMenu(navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.Create.route) },
                containerColor = Color(0xFF1A48E3),
                contentColor = Color.White            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Icono de Inicio"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Listado de Items")

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items) { item ->
                    key(item) {
                        ItemCard(
                            item = item,
                            itemsViewModel = itemsViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(navController: NavController, item: Item, itemsViewModel: ItemsViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            expanded = !expanded
        },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(1.dp,Color.Gray)
    ) {
        Column (modifier = Modifier.padding(20.dp)){
            if (!expanded) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ExpandMore,
                        contentDescription = "Expandir"
                    )
                    Text(item.name)
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ExpandLess,
                        contentDescription = "Contraer"
                    )
                    Text(item.name)
                }

                Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.description)
                        Spacer(modifier = Modifier.height(10.dp))
                        AsyncImage(
                            model = "android.resource://com.example.storetrack/drawable/${item.photo}",
                            contentDescription = "Foto de ${item.name}",
                            modifier = Modifier
                                .size(150.dp),
                            contentScale = ContentScale.Crop,

                            placeholder = painterResource(R.drawable.ic_loading),
                            error = painterResource(R.drawable.ic_broken_image)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = { showDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Icono de eliminar"
                            )
                        }
                        IconButton(onClick = {
                            navController.navigate(
                                Screen.Details.createRoute(
                                    item.id
                                )
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Icono de editar"
                            )
                        }
                    }
                }
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Cuidado") },
                    text = { Text("¿Desea eliminar permanentemente este Item?.") },
                    confirmButton = {
                        Button(onClick = { itemsViewModel.removeItem(item.id) }) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}