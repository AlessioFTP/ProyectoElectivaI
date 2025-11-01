package com.example.proyectoelectivai.datos.modelo

data class Guia(
    val idGuia: String = "",
    val tituloJuego: String = "",
    val tituloJuegoMinuscula: String = "",
    val descripcion: String = "",
    val imagenPortada: String = "",
    val usuarioCreador: String = "",
    val apartados: List<Apartado> = emptyList(),
    val uidUsuariosFavoritos: MutableList<String> = mutableListOf()
)
