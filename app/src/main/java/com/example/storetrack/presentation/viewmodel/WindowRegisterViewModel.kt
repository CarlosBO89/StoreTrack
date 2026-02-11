package com.example.storetrack.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WindowRegisterViewModel : ViewModel() {

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError
    private val _nameError = MutableStateFlow<String?>(null)
    val nameError: StateFlow<String?> = _nameError

    private val _surnameError = MutableStateFlow<String?>(null)
    val surnameError: StateFlow<String?> = _surnameError

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _surname = MutableStateFlow("")
    val surname: StateFlow<String> = _surname

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError


    fun setName(name: String) {
        if (name.length <= 64) {
            _name.value = name
            validateName(name)
        }
    }

    fun setEmail(email: String) {
        _email.value = email
        validateEmail(email)
    }

    fun setSurname(surname: String) {
        if (surname.length <= 64) {
            _surname.value = surname
            validateSurname(surname)
        }
    }

    fun setPassword(password: String) {
        if (password.length <= 24) {
            _password.value = password
            validatePassword(password)
        }
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }


    private fun validateName(name: String) {
        _nameError.value = when {
            name.isBlank() -> "El nombre no puede estar vacío"
            name.length < 3 -> "Mínimo 3 caracteres"
            else -> null
        }
    }

    private fun validateSurname(surname: String) {
        _surnameError.value = when {
            surname.isBlank() -> "El apellido no puede estar vacío"
            surname.length < 3 -> "Mínimo 3 caracteres"
            else -> null
        }
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
}