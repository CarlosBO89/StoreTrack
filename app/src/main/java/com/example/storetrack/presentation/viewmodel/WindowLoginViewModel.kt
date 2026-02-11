package com.example.storetrack.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WindowLoginViewModel : ViewModel() {
    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError
    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


    fun setEmail(email: String) {
        _email.value = email
        validateEmail(email)
    }
    private fun validateEmail(email: String) {
        _emailError.value = when {
            email.isBlank() -> "El correo es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Correo no válido"
            else -> null
        }
    }
    private fun validatePassword(password: String) {
        _passwordError.value = when {
            password.isBlank() -> "La contraseña no puede estar vacía"
            password.length < 8 -> "Mínimo 8 caracteres"
            else -> null
        }
    }

    fun setPassword(password: String) {
        _password.value = password
        validatePassword(password)
    }
}