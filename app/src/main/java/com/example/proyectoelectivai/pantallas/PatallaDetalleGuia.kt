package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectoelectivai.componentes.ApartadosDetalleGuia
import com.example.proyectoelectivai.componentes.ImagenSuperiorDetalleGuia
import com.example.proyectoelectivai.datos.modelo.Guia
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun PantallaDetalleGuia(
    navController: NavController,
    modifier: Modifier = Modifier,
    idGuia: String,
    usuarioCreador: String
) {
    var guia by remember { mutableStateOf<Guia?>(null) }

    LaunchedEffect(idGuia) {
        guia = buscarGuiaPorId(idGuia)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(
                RoundedCornerShape(
                    15.dp
                )
            )
            .background(color = Color(0xFF161516))
    ) {
        guia?.let { datosGuia ->
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                ImagenSuperiorDetalleGuia(datosGuia, usuarioCreador, navController)

                ApartadosDetalleGuia(datosGuia)

            }
        } ?: Text(
            text = "Cargando guía...",
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


suspend fun buscarGuiaPorId(idGuia: String): Guia? {
    val db = FirebaseFirestore.getInstance()
    val snapshot = db.collection("guias").document(idGuia).get().await()
    return snapshot.toObject(Guia::class.java)
}





