package com.example.storetrack.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.storetrack.presentation.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ActionMenu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .padding()
            .clip(RoundedCornerShape(24.dp)),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1A48E3),
                titleContentColor = Color.White
            ),
            title = { Text("Menú de acciones") },
            actions = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = Color.White
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = Color(0xFF1A48E3)
                ) {
                    DropdownMenuItem(
                        text = { Text("LogIn") },
                        onClick = {
                            expanded = false
                            navController.navigate(Screen.Login.route)
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.White
                        )
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Back")
                        },
                        onClick = {
                            expanded = false
                            navController.popBackStack()
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.White
                        )
                    )
                    HorizontalDivider()
                    DropdownMenuItem(
                        text = {
                            Text("Other action")
                        },
                        onClick = {
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.White
                        )
                    )
                }
            }
        )
    }
}