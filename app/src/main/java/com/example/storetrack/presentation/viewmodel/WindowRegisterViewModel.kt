package com.example.storetrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WindowRegisterViewModel : ViewModel() {
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


    fun setName(name: String) {
        _name.value = name
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setSurname(surname: String) {
        _surname.value = surname
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }
}