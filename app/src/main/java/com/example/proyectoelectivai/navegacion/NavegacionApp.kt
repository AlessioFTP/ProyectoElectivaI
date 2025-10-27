package com.example.proyectoelectivai.navegacion

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectoelectivai.pantallas.*

@Composable
fun NavegacionApp(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = PantallasApp.PantallaInicio.ruta,
        modifier = modifier.fillMaxSize()
    ) {
        composable(PantallasApp.PantallaInicio.ruta) {
            PantallaInicio(navController, modifier)
        }
        composable(PantallasApp.PantallaMisGuias.ruta) {
            PantallaMisGuias(navController, modifier)
        }
        composable(PantallasApp.PantallaNotificaciones.ruta) {
            PantallaNotificaciones(navController, modifier)
        }
        composable(PantallasApp.PantallaPerfil.ruta) {
            PantallaPerfil(navController, modifier)
        }
        composable(PantallasApp.PantallaNuevaGuia.ruta) {
            PantallaNuevaGuia(navController, modifier)
        }
        composable(PantallasApp.PantallaInicioSesion.ruta) {
            PantallaInicioSesion(navController, modifier)
        }
        composable(PantallasApp.PantallaCrearCuenta.ruta) {
            PantallaCrearCuenta(navController, modifier)
        }
        composable(PantallasApp.PantallaPerfilSinLoguear.ruta) {
            PantallaPerfilSinLoguear(navController, modifier)
        }
        composable(PantallasApp.PantallaPerfilLogueado.ruta) {
            PantallaPerfilLogueado(navController, modifier)
        }
    }
}
