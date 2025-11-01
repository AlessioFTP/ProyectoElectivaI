package com.example.proyectoelectivai.datos.modelo

data class Usuario(
    val uid: String = "",
    val usuario: String = "",
    val correo: String = "",
    val urlImagenPerfil: String = "",
    val notificaciones: MutableList<Notificacion> = mutableListOf()
)
