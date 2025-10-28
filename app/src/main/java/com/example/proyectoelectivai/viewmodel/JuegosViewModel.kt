package com.example.proyectoelectivai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoelectivai.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class JuegosViewModel(application: Application): AndroidViewModel(application) {

    private val _todosLosJuegos = MutableStateFlow<List<String>>(emptyList())
    val todosLosJuegos = _todosLosJuegos.asStateFlow()

    private val _juegosFiltrados = MutableStateFlow<List<String>>(emptyList())
    val juegosFiltrados = _juegosFiltrados.asStateFlow()

    private val _cargando = MutableStateFlow(true)

    init{
        viewModelScope.launch(Dispatchers.IO) {
            val input = application.resources.openRawResource(R.raw.nombre_juegos)
            val json = input.bufferedReader().use { it.readText() }
            val arr = JSONObject(json).getJSONArray("juegos")

            val listaJuegos = List(arr.length()) { i -> arr.getString(i) }
            _todosLosJuegos.value = listaJuegos
            _cargando.value = false
        }
    }

    fun filtrarJuegos(consulta: String) {
        viewModelScope.launch(Dispatchers.Default){
            if(consulta.isBlank()){
                _juegosFiltrados.value = emptyList()
            }else {
                val filtrados = _todosLosJuegos.value.filter {
                    it.contains(consulta, ignoreCase = true)
                }.take(50)

                _juegosFiltrados.value = filtrados
            }

        }
    }

    fun juegoExiste(nombre: String): Boolean {
        return _todosLosJuegos.value.any { it.equals(nombre, ignoreCase = true) }
    }
}