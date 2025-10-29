package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoelectivai.navegacion.NavegacionApp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel

@Composable
fun BotonNuevaTarea(navController: NavController, currentDestination: String?) {
    val buttonSize = 70.dp
    val lineStroke = 2.dp
    val buttonSizePx = with(LocalDensity.current) { buttonSize.toPx() }
    val lineStrokePx = with(LocalDensity.current) { lineStroke.toPx() }

    val viewModelUsuario: AutenticarViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val y = buttonSizePx / 2
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = lineStrokePx
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModelUsuario.usuarioLogueado()) {
            IconButton(
                onClick = {
                    navController.navigate(PantallasApp.PantallaNuevaGuia.ruta) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .size(buttonSize)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .border(
                        if (currentDestination == PantallasApp.PantallaNuevaGuia.ruta) BorderStroke(
                            2.dp,
                            Color.White
                        ) else BorderStroke(0.dp, Color.Transparent),
                        shape = CircleShape
                    )
            ) {

                Icon(
                    painter = painterResource(R.drawable.icono_nueva_guia),
                    contentDescription = "Nueva Guia",
                    modifier = Modifier.size(
                        if (currentDestination == PantallasApp.PantallaNuevaGuia.ruta) 48.dp else 40.dp
                    ),
                    tint = if (currentDestination == PantallasApp.PantallaNuevaGuia.ruta) Color.White else Color.DarkGray,
                )
            }
        }
    }
}


@Composable
fun BarraNavegacion() {
    val navController = rememberNavController()
    val viewModelUsuario: AutenticarViewModel = viewModel()

    Scaffold(
        containerColor = Color.Transparent,

        bottomBar = {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route

            BotonNuevaTarea(navController, currentDestination)

            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = Color.Transparent,
                modifier = Modifier.padding(top = 45.dp)
            ) {

                OpcionesBarraNavegacion.entries.forEach { opcion ->
                    NavigationBarItem(
                        selected = currentDestination == opcion.ruta,
                        onClick = {
                            if (viewModelUsuario.usuarioLogueado() || (opcion.ruta != OpcionesBarraNavegacion.MIS_GUIAS.ruta && opcion.ruta != OpcionesBarraNavegacion.NOTIFICACIONES.ruta)) {
                                navController.navigate(opcion.ruta) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            }

                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = opcion.icono),
                                contentDescription = opcion.contentDescription,
                                modifier = Modifier.size(
                                    if (currentDestination == opcion.ruta) 48.dp else 40.dp
                                ),
                            )

                        },
                        label = { Text(opcion.label) },

                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.Gray,
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )

                    )
                }
            }
        }
    ) { contentPadding ->
        NavegacionApp(navController = navController, modifier = Modifier.padding(contentPadding))
    }
}
