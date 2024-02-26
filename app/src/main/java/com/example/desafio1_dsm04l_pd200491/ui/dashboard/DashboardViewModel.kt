package com.example.desafio1_dsm04l_pd200491.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ejercicio 2: Salario Neto"
    }
    val text: LiveData<String> = _text
}