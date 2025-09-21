package com.example.proyectoelectivai.navegacion

sealed class PantallasApp(val ruta: String) {
    object PantallaInicio : PantallasApp("pantalla_inicio")
    object PantallaMisGuias : PantallasApp("pantalla_mis_guias")
    object PantallaNotificaciones : PantallasApp("pantalla_notificaciones")
    object PantallaPerfil : PantallasApp("pantalla_perfil")
    object pantallaNuevaGuia : PantallasApp("pantalla_nueva_guia")

}