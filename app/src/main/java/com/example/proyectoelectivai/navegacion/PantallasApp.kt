package com.example.proyectoelectivai.navegacion

sealed class PantallasApp(val ruta: String) {
    object PantallaInicio : PantallasApp("pantalla_inicio")
    object PantallaMisGuias : PantallasApp("pantalla_mis_guias")
    object PantallaNotificaciones : PantallasApp("pantalla_notificaciones")
    object PantallaPerfil : PantallasApp("pantalla_perfil")
    object PantallaPerfilSinLoguear : PantallasApp("pantalla_perfil_sin_loguear")
    object PantallaPerfilLogueado : PantallasApp("pantalla_perfil_logueado")
    object PantallaNuevaGuia : PantallasApp("pantalla_nueva_guia")
    object PantallaInicioSesion : PantallasApp("pantalla_inicio_sesion")
    object PantallaCrearCuenta : PantallasApp("pantalla_crear_cuenta")
}