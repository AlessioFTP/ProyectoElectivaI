package com.example.proyectoelectivai.datos.modelo

data class Notificacion(
    val mensaje: String = "",
    val imagenUsuarioCreador: String = "",
    val imagenGuia: String = "",
    var leida: Boolean = false,
    val idGuia: String = "",
 )
