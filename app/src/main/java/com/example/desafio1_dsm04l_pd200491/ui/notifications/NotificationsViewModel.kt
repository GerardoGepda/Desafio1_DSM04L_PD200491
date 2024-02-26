package com.example.desafio1_dsm04l_pd200491.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ejercicio 3: Calculadora"
    }
    val text: LiveData<String> = _text
}