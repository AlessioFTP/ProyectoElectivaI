package com.example.proyectoelectivai.navegacion

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(
            route = PantallasApp.PantallaEditarGuia.ruta,
            arguments = listOf(navArgument("idGuia") { type = NavType.StringType })
        ) { backStackEntry ->
            val idGuia = backStackEntry.arguments?.getString("idGuia") ?: ""
            PantallaEditarGuia(navController, modifier, idGuia)
        }
        composable(
            route = PantallasApp.PantallaDetalleGuia.ruta,
            arguments = listOf(
                navArgument("idGuia") { type = NavType.StringType },
                navArgument("usuarioCreador") { type = NavType.StringType })
        ) { backStackEntry ->
            val idGuia = backStackEntry.arguments?.getString("idGuia") ?: ""
            val usuarioCreador = backStackEntry.arguments?.getString("usuarioCreador") ?: ""
            PantallaDetalleGuia(navController, modifier, idGuia, usuarioCreador)
        }

    }
}
