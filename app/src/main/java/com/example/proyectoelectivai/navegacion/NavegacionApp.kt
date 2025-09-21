package com.example.proyectoelectivai.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.proyectoelectivai.pantallas.PantallaInicio
import com.example.proyectoelectivai.pantallas.PantallaMisGuias
import com.example.proyectoelectivai.pantallas.PantallaNotificaciones
import com.example.proyectoelectivai.pantallas.PantallaPerfil
import com.example.proyectoelectivai.pantallas.PantallaNuevaGuia


@Composable
fun NavegacionApp(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = PantallasApp.PantallaInicio.ruta,
        modifier = modifier
    ) {
        composable(route = PantallasApp.PantallaInicio.ruta) {
            PantallaInicio(navController, modifier)
        }
        composable(route = PantallasApp.PantallaMisGuias.ruta) {
            PantallaMisGuias(navController, modifier)
        }
        composable(route = PantallasApp.PantallaNotificaciones.ruta) {
            PantallaNotificaciones(navController, modifier)
        }
        composable(route = PantallasApp.PantallaPerfil.ruta) {
            PantallaPerfil(navController, modifier)
        }
        composable(route = PantallasApp.pantallaNuevaGuia.ruta){
            PantallaNuevaGuia(navController, modifier)
        }
    }
}


