package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.datos.modelo.Guia
import com.example.proyectoelectivai.datos.modelo.Usuario
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.pantallas.obtenerDatosUsuario
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ImagenSuperiorDetalleGuia(
    datosGuia: Guia,
    usuarioCreador: String,
    navController: NavController
) {
    val viewModelAutenticar: AutenticarViewModel = viewModel()

    var usuarioLogueado by remember { mutableStateOf("") }
    var datosUsuario by remember { mutableStateOf<Usuario?>(null) }

    if (viewModelAutenticar.usuarioLogueado()) {
        val uid = viewModelAutenticar.obtenerUidActual()!!
        LaunchedEffect(uid) {
            datosUsuario = obtenerDatosUsuario(uid)
            usuarioLogueado = datosUsuario?.usuario ?: ""
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
    ) {
        AsyncImage(
            model = datosGuia.imagenPortada,
            contentDescription = "Grid Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCE000000))
        )
        if (usuarioCreador == usuarioLogueado) {
            IconButton(
                onClick = {
                    navController.navigate(
                        PantallasApp.PantallaEditarGuia.crearRuta(
                            datosGuia.idGuia
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(70.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icono_editar_guia),
                    contentDescription = "Nueva Guia",
                    modifier = Modifier.size(48.dp),
                    tint = Color.White
                )
            }
        } else if (!usuarioLogueado.isEmpty()) {
            var guardadaPorUsuario by remember { mutableStateOf(false) }

            if (datosUsuario != null) {
                guardadaPorUsuario = datosGuia.uidUsuariosFavoritos.contains(datosUsuario!!.uid)
            }

            var iconoFavorito by remember { mutableIntStateOf(0) }

            if (guardadaPorUsuario) {
                iconoFavorito = R.drawable.icono_guia_en_favoritos
            } else {
                iconoFavorito = R.drawable.icono_poner_guia_favoritos
            }
            IconButton(
                onClick = {
                    guardadaPorUsuario = !guardadaPorUsuario

                    if (guardadaPorUsuario) {
                        datosGuia.uidUsuariosFavoritos.add(datosUsuario!!.uid)
                    } else {
                        datosGuia.uidUsuariosFavoritos.remove(datosUsuario!!.uid)
                    }
                    actualizarCampoFavoritos(datosGuia.idGuia, datosGuia.uidUsuariosFavoritos)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(70.dp)
            ) {
                Icon(
                    painter = painterResource(iconoFavorito),
                    contentDescription = "Favoritos",
                    modifier = Modifier.size(48.dp),
                    tint = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = datosGuia.imagenPortada,
                contentDescription = "Grid Image",
                modifier = Modifier
                    .size(180.dp)
                    .padding(end = 20.dp, start = 20.dp, top = 20.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = datosGuia.descripcion,
                color = Color.White,
                maxLines = 2,
                fontSize = 15.sp,
                textAlign = TextAlign.Center

            )
        }

    }
}

fun actualizarCampoFavoritos(guiaId: String, nuevosFavoritos: List<String>) {
    val db = FirebaseFirestore.getInstance()
    db.collection("guias").document(guiaId)
        .update("uidUsuariosFavoritos", nuevosFavoritos)
}
