package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoelectivai.navegacion.NavegacionApp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BarraNavegacion() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route

                OpcionesBarraNavegacion.entries.forEach { opcion ->
                    NavigationBarItem(
                        selected = currentDestination == opcion.ruta,
                        onClick = {
                            navController.navigate(opcion.ruta) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = opcion.icono),
                                contentDescription = opcion.contentDescription
                            )
                        },
                        label = { Text(opcion.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavegacionApp(navController = navController, modifier = Modifier.padding(contentPadding))
    }
}
