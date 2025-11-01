package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoelectivai.componentes.Notificacion
import com.example.proyectoelectivai.datos.modelo.Usuario
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PantallaNotificaciones(navController: NavController, modifier: Modifier = Modifier) {

    val viewModelUsuario: AutenticarViewModel = viewModel()

    if (!viewModelUsuario.usuarioLogueado()) {
        navController.navigate(PantallasApp.PantallaInicio.ruta) {
            popUpTo(PantallasApp.PantallaMisGuias.ruta) { inclusive = true }
        }
        return
    }

    val uid = viewModelUsuario.obtenerUidActual()!!
    var datosUsuario by remember { mutableStateOf<Usuario?>(null) }
    var cargando by remember { mutableStateOf(true) }
    var notificaciones by remember { mutableStateOf(listOf<com.example.proyectoelectivai.datos.modelo.Notificacion>()) }

    LaunchedEffect(uid) {
        datosUsuario = obtenerDatosUsuario(uid)
        cargando = false
        if (datosUsuario != null) {
            notificaciones = datosUsuario!!.notificaciones.toList()
        }
    }

    val hayNotificacionesMostrables = notificaciones.any { !it.leida }

    when {
        cargando -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        notificaciones.isEmpty() || !hayNotificacionesMostrables -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(color = Color(0xFF161516), shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "No tienes notificaciones pendientes.",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            navController.navigate(PantallasApp.PantallaInicio.ruta)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000))
                    ) {
                        Text("Ir al inicio.")
                    }
                }
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                notificaciones.filter { !it.leida }.forEach { notificacion ->
                    Notificacion(
                        imagenPerfil = notificacion.imagenUsuarioCreador,
                        mensaje = notificacion.mensaje,
                        imagenJuego = notificacion.imagenGuia,
                        modifier = Modifier.clickable {
                            notificaciones = notificaciones.map {
                                if (it.idGuia == notificacion.idGuia) it.copy(leida = true) else it
                            }
                            datosUsuario =
                                datosUsuario?.copy(notificaciones = notificaciones.toMutableList())
                            actualizarNotificacionLeida(datosUsuario!!.uid, notificacion.idGuia)
                            navController.navigate(
                                PantallasApp.PantallaDetalleGuia.crearRuta(
                                    notificacion.idGuia,
                                    datosUsuario!!.uid
                                )
                            )
                        }
                    )
                }
            }
        }
    }

}


fun actualizarNotificacionLeida(uid: String, idGuia: String) {
    val db = FirebaseFirestore.getInstance()
    val userRef = db.collection("usuarios").document(uid)

    userRef.get()
        .addOnSuccessListener { snapshot ->
            val usuario = snapshot.toObject(Usuario::class.java)
            usuario?.let {
                val nuevasNotificaciones = it.notificaciones.map { n ->
                    if (n.idGuia == idGuia) n.copy(leida = true) else n
                }
                userRef.update("notificaciones", nuevasNotificaciones)
            }
        }
}

