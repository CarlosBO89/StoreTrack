package com.example.storetrack.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import com.example.storetrack.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.storetrack.presentation.navigation.Screen
import com.example.storetrack.presentation.viewmodel.WindowLoginViewModel


@Composable
fun LogIn(navController: NavController, windowLoginViewModel: WindowLoginViewModel = viewModel()) {
    val passwordError by windowLoginViewModel.passwordError.collectAsState()
    val emailError by windowLoginViewModel.emailError.collectAsState()
    val email by windowLoginViewModel.email.collectAsState()
    val password by windowLoginViewModel.password.collectAsState()
    val buttonEnabled by remember { derivedStateOf { emailError == null && passwordError == null &&
                    email.isNotBlank() && password.isNotBlank()
        }
    }
    var passwordVisible by remember { mutableStateOf(false) }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de StoreTrack"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Iniciar sesión",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Column {
                TextField(
                    value = email,
                    onValueChange = { windowLoginViewModel.setEmail(it) },
                    label = { Text("Correo electrónico", color = Color.DarkGray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.DarkGray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    textStyle = TextStyle(color = Color.DarkGray),
                    singleLine = true
                )
                emailError?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Column {
                TextField(
                    value = password,
                    onValueChange = { windowLoginViewModel.setPassword(it) },
                    label = { Text("Contraseña", color = Color.DarkGray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.DarkGray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    textStyle = TextStyle(color = Color.DarkGray),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                    trailingIcon = {
                        val image =
                            if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    }
                )
                passwordError?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(Screen.Items.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = buttonEnabled
            ) {
                Text("Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¿No tienes cuenta?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
                    Text(
                        text = "Regístrate",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }
            Spacer(modifier = Modifier.height(210.dp))

            Text(
                text = "© 2025 StoreTrack. Todos los derechos reservados.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}