package com.example.proyectoelectivai.pantallas


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.componentes.BloqueRecomendaciones
import com.example.proyectoelectivai.componentes.CategoriaDeJuegos
import com.example.proyectoelectivai.navegacion.PantallasApp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.LaunchedEffect
import com.example.proyectoelectivai.componentes.TarjetaMiGuia
import com.example.proyectoelectivai.viewmodel.SteamGridViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PantallaBusquedaGuia(navController: NavController, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    val viewModel: SteamGridViewModel = viewModel()

    // lista de títulos
    val guiasMinecraft = listOf(
        "Guía speedruns básica",
        "Guía Minecraft",
        "Redstone Avanzado",
        "Guía PVP Minecraft",
        "Construcción creativa",
        "Mods imprescindibles"
    )

    var imagenesMinecraft by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.busquedaYCargaDeGrids("minecraft") { result ->
            imagenesMinecraft = result.map { it.thumb }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BarraBusqueda(query = query, onQueryChange = { query = it })

        Spacer(Modifier.height(16.dp))

        for (i in guiasMinecraft.indices step 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val imagenUrl1 = imagenesMinecraft.getOrNull(i)
                TarjetaMiGuia(
                    titulo = guiasMinecraft[i],
                    imagenUrl = imagenUrl1,
                    imagenRes = if (imagenUrl1 == null) R.drawable.alex else null,
                    onClick = { /* navega si quieres */ },
                    juego = "minecraft"
                )

                if (i + 1 < guiasMinecraft.size) {
                    val imagenUrl2 = imagenesMinecraft.getOrNull(i + 1)
                    TarjetaMiGuia(
                        titulo = guiasMinecraft[i + 1],
                        imagenUrl = imagenUrl2,
                        imagenRes = if (imagenUrl2 == null) R.drawable.alex else null,
                        onClick = { /* navega si quieres */ },
                        juego = "minecraft"
                    )
                } else {
                    Spacer(modifier = Modifier.width(180.dp))
                }
            }
        }
    }
}