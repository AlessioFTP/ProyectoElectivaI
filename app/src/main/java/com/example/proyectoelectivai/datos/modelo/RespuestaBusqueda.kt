package com.example.proyectoelectivai.datos.modelo

data class RespuestaBusqueda(
    val success: Boolean,
    val data: List<Juego>
)