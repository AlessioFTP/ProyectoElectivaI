package com.example.proyectoelectivai.navegacion

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectoelectivai.pantallas.*
import com.example.proyectoelectivai.ui.screens.PantallaDetalleGuia
import com.example.proyectoelectivai.R

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
        composable(PantallasApp.pantallaNuevaGuia.ruta) {
            PantallaNuevaGuia(navController, modifier)
        }
        composable(PantallasApp.PantallaInicioSesion.ruta) {
            PantallaInicioSesion(navController, modifier)
        }
        composable(PantallasApp.PantallaCrearCuenta.ruta) {
            PantallaCrearCuenta(navController, modifier)
        }

        // Pantalla de detalle de guía
        composable("detalle/{titulo}/{descripcion}/{imagenRes}") { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo") ?: "Sin título"
            val descripcion = Uri.decode(backStackEntry.arguments?.getString("descripcion") ?: "")
            val imagenRes = backStackEntry.arguments?.getString("imagenRes")?.toIntOrNull()
                ?: R.drawable.ic_launcher_foreground

            PantallaDetalleGuia(
                titulo = titulo,
                descripcion = descripcion,
                imagenRes = imagenRes
            )
        }
    }
}
