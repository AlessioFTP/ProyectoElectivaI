package com.example.proyectoelectivai.datos.modelo

data class Apartado(
    val titulo: String = "",
    val contenido: List<Bloque> = emptyList()
)
