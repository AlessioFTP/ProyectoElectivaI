package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyectoelectivai.datos.modelo.Grid
import com.example.proyectoelectivai.viewmodel.SteamGridViewModel
import kotlin.collections.firstOrNull

@Composable
fun Notificacion(
    imagenPerfil: Int,
    mensaje: String,
    imagenJuego: String,
    viewModel: SteamGridViewModel = viewModel()
) {

    var gridPorJuego by remember { mutableStateOf<Map<String, List<Grid>>>(emptyMap()) }

    viewModel.busquedaYCargaDeGrids(imagenJuego) { result ->
        gridPorJuego = gridPorJuego + (imagenJuego to result)
    }

    val grid = gridPorJuego[imagenJuego]?.firstOrNull()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = imagenPerfil),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Box(
            modifier = Modifier
                .height(100.dp)
                .width(250.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = Color.DarkGray)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = mensaje,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(160.dp)
                        .padding(end = 10.dp),
                    fontWeight = Bold
                )

                if (grid != null) {
                    AsyncImage(
                        model = grid.thumb,
                        contentDescription = "Grid Image",
                        modifier = Modifier.size(100.dp),
                    )
                }

            }
        }
    }

}