package com.example.proyectoelectivai.pantallas

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.componentes.TarjetaMiGuia

@Composable
fun PantallaMisGuias(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Guía Balatro
            TarjetaMiGuia(
                titulo = "Todo sobre Balatro!",
                imagenRes = R.drawable.balatro,
                onClick = {
                    val desc = Uri.encode(
                        "El juego es 7 Days to Die, un videojuego de mundo abierto que combina disparos en primera persona, supervivencia de terror, defensa de torres y rol en un mundo postapocalíptico infestado de no-muertos. Los jugadores exploran, construyen, fabrican objetos, recolectan recursos y combaten hordas de zombis, culminando en eventos como la Horda de la Luna de Sangre. El juego salió de acceso anticipado en julio de 2024, y se puede jugar en PC y consolas"
                    )
                    navController.navigate("detalle/Todo sobre Balatro!/$desc/${R.drawable.balatro}")
                }
            )

            // Guía 7 Days
            TarjetaMiGuia(
                titulo = "Guía 7 Days",
                imagenRes = R.drawable.seven_days,
                onClick = {
                    val desc = Uri.encode(
                        "El juego es 7 Days to Die, un videojuego de mundo abierto que combina disparos en primera persona, supervivencia de terror, defensa de torres y rol en un mundo postapocalíptico infestado de no-muertos. Los jugadores exploran, construyen, fabrican objetos, recolectan recursos y combaten hordas de zombis, culminando en eventos como la Horda de la Luna de Sangre. El juego salió de acceso anticipado en julio de 2024, y se puede jugar en PC y consolas."
                    )
                    navController.navigate("detalle/Guía 7 Days/$desc/${R.drawable.seven_days}")
                }
            )
        }
    }
}
