package com.example.proyectoelectivai.pantallas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel

@Composable
fun PantallaPerfil(navController: NavController, modifier: Modifier = Modifier) {
    val viewModel: AutenticarViewModel = viewModel()
    if (viewModel.usuarioLogueado()){
        navController.navigate("pantalla_perfil_logueado")
    } else {
        navController.navigate("pantalla_perfil_sin_loguear")
    }
}