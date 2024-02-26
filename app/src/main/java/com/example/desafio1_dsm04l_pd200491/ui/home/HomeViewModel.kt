package com.example.desafio1_dsm04l_pd200491.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ejercicio 1: Promedio"
    }
    val text: LiveData<String> = _text
}