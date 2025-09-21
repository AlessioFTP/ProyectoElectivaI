package com.example.proyectoelectivai.componentes

import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.navegacion.PantallasApp

enum class OpcionesBarraNavegacion(
    val ruta: String,
    val label: String,
    val icono: Int,
    val contentDescription: String
) {
    INICIO(PantallasApp.PantallaInicio.ruta, "Inicio", R.drawable.icono_inicio, "Inicio"),
    MIS_GUIAS(PantallasApp.PantallaMisGuias.ruta, "Mis Guias", R.drawable.icono_guias, "Mis Guias"),
    NOTIFICACIONES(PantallasApp.PantallaNotificaciones.ruta, "Notificaciones", R.drawable.icono_notificaciones, "Notificaciones"),
    PERFIL(PantallasApp.PantallaPerfil.ruta, "Perfil", R.drawable.icono_perfil, "Perfil")


}