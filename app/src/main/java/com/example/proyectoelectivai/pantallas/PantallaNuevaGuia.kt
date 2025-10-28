package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
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
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.datos.modelo.Grid
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel
import com.example.proyectoelectivai.viewmodel.JuegosViewModel
import com.example.proyectoelectivai.viewmodel.SteamGridViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PantallaNuevaGuia(navController: NavController, modifier: Modifier = Modifier) {
    var viewModelUsuario: AutenticarViewModel = viewModel()
    if (!viewModelUsuario.usuarioLogueado()) {
        navController.navigate(PantallasApp.PantallaInicio.ruta)
    }
    val uid = viewModelUsuario.obtenerUidActual()!!
    var datosUsuario by remember { mutableStateOf<Map<String, Any>?>(null) }
    LaunchedEffect(uid) {
        datosUsuario = obtenerDatosUsuario(uid)
    }

    val usuarioCreador = datosUsuario?.get("usuario") as? String

    val viewModelJuegos: JuegosViewModel = viewModel()
    val resultados by viewModelJuegos.juegosFiltrados.collectAsState()

    var mostrarImagenes by remember { mutableStateOf(false) }

    var tituloJuego by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var gridSeleccionado by remember { mutableStateOf("") }

    var tieneFocoBusqueda by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.DarkGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BarraBusqueda(
                query = tituloJuego,
                onQueryChange = {
                    tituloJuego = it
                    viewModelJuegos.filtrarJuegos(it)
                },
                onFocusChanged = { enfocado ->
                    tieneFocoBusqueda = enfocado
                }
            )

            if (tieneFocoBusqueda && resultados.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                        .padding(horizontal = 30.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(15.dp))
                        .clip(RoundedCornerShape(15.dp))

                ) {
                    items(resultados) { nombre ->
                        Text(
                            text = nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    tituloJuego = nombre
                                    viewModelJuegos.filtrarJuegos("")
                                }
                                .padding(vertical = 6.dp, horizontal = 10.dp),
                            color = Color.White
                        )
                        Divider(color = Color.White)
                    }
                }
            }

            Column() {

                if (mostrarImagenes && viewModelJuegos.juegoExiste(tituloJuego)) {
                    ImagenesJuego(
                        tituloJuego,
                        viewModelJuegos,
                        onGridSleccionado = { gridUrl ->
                            gridSeleccionado = gridUrl
                            mostrarImagenes = false
                        })
                } else {
                    if (gridSeleccionado.isNotEmpty() && viewModelJuegos.juegoExiste(tituloJuego)) {
                        Card(
                            modifier = Modifier
                                .width(158.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterHorizontally)
                                .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp))
                                .clip(RoundedCornerShape(15.dp)),
                            elevation = CardDefaults.cardElevation(0.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                        ) {
                            AsyncImage(
                                model = gridSeleccionado,
                                contentDescription = "Grid Image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent),
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else {
                        gridSeleccionado = ""
                        Image(
                            painter = painterResource(id = R.drawable.imagen_nueva_guia),
                            contentDescription = "Imagen nueva guia",
                            modifier = Modifier
                                .size(230.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                        )
                    }

                    var textoAgregarImagen = if (gridSeleccionado.isNotEmpty()) {
                        "Cambiar Imagen"
                    } else {
                        "Agregar Imagen"
                    }
                    Text(
                        text = textoAgregarImagen,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                mostrarImagenes = true
                                gridSeleccionado = ""
                            },
                        fontSize = 30.dp.value.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }

            }

            TextField(
                value = descripcion,
                onValueChange = { nuevoTexto ->
                    if (nuevoTexto.length <= 32) {
                        descripcion = nuevoTexto
                    }
                },
                label = { Text("Descripción (Máximo 32 carácteres)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .border(width = 4.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.LightGray
                )
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        guardarGuia(
                            tituloJuego,
                            descripcion,
                            gridSeleccionado,
                            usuarioCreador!!
                        )
                        navController.navigate(PantallasApp.PantallaMisGuias.ruta) {
                            popUpTo(PantallasApp.PantallaNuevaGuia.ruta) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .border(BorderStroke(2.dp, Color.White), shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icono_boton_aceptar_nueva_guia),
                        contentDescription = "Nueva Guia",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ImagenesJuego(
    tituloJuego: String,
    viewModelJuegos: JuegosViewModel,
    onGridSleccionado: (String) -> Unit = {}
) {
    val viewModelGrid: SteamGridViewModel = viewModel()
    var gridJuego by remember { mutableStateOf<Map<String, List<Grid>>>(emptyMap()) }

    LaunchedEffect(tituloJuego) {
        if (tituloJuego.isNotBlank() && viewModelJuegos.juegoExiste(tituloJuego)) {
            viewModelGrid.busquedaYCargaDeGrids(tituloJuego) { result ->
                gridJuego = gridJuego + (tituloJuego to result)
            }
        }
    }

    val grids = gridJuego[tituloJuego]?.take(20) ?: emptyList()

    if (grids.isNotEmpty()) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            items(grids) { grid ->
                Card(
                    modifier = Modifier
                        .width(180.dp)
                        .fillMaxHeight()
                        .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp))
                        .clip(RoundedCornerShape(15.dp))
                        .clickable { onGridSleccionado(grid.thumb) },
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    AsyncImage(
                        model = grid.thumb,
                        contentDescription = "Grid Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

fun guardarGuia(
    tituloJuego: String,
    descripcion: String,
    gridUrl: String,
    usuarioCreador: String
) {
    val db = FirebaseFirestore.getInstance()
    val guiasRef = db.collection("guias")
        .document(usuarioCreador)
        .collection("guias")

    val nuevaGuia = guiasRef.document()
    val idGenerado = nuevaGuia.id

    val guia = hashMapOf(
        "idGuia" to idGenerado,
        "tituloJuego" to tituloJuego,
        "descripcion" to descripcion,
        "imagenPortada" to gridUrl
    )

    nuevaGuia.set(guia)
}




