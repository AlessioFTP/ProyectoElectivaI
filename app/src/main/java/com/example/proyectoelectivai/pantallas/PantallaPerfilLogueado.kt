package com.example.proyectoelectivai.pantallas

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

@Composable
fun PantallaPerfilLogueado(navController: NavController, modifier: Modifier = Modifier) {
    val viewModel: AutenticarViewModel = viewModel()
    val uid = viewModel.obtenerUidActual()!!

    var datosUsuario by remember { mutableStateOf<Map<String, Any>?>(null) }
    LaunchedEffect(uid) {
        datosUsuario = obtenerDatosUsuario(uid)
    }

    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagenUri = uri
    }

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
                .padding(vertical = 40.dp),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { launcher.launch("image/*") }
            ) {
                when {
                    imagenUri != null -> AsyncImage(
                        model = imagenUri,
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    datosUsuario?.get("urlImagenPerfil") != null -> AsyncImage(
                        model = datosUsuario!!["urlImagenPerfil"],
                        contentDescription = "Imagen guardada en Firebase",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    else -> Image(
                        painter = painterResource(id = R.drawable.icono_perfil),
                        contentDescription = "Imagen de perfil por defecto",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (imagenUri != null) {
                Button(
                    onClick = {
                        subirImagenAFirebase(imagenUri!!, uid)
                        navController.navigate("pantalla_perfil")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text(
                        text = "Subir imagen",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            Text(
                text = datosUsuario?.get("usuario") as? String ?: "Usuario",
                fontSize = 30.dp.value.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.cerrarSesion()
                    navController.navigate("pantalla_perfil")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(
                    text = "Cerrar Sesión",
                    fontSize = 20.dp.value.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}

suspend fun obtenerDatosUsuario(uid: String): Map<String, Any>? {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("usuarios").document(uid)

    val snapshot = docRef.get().await()
    return if (snapshot.exists()) snapshot.data else null
}

fun subirImagenAFirebase(uri: Uri, uid: String) {
    val referenciaAlmacenamiento = FirebaseStorage.getInstance().reference
    val referenciaImagen =
        referenciaAlmacenamiento.child("usuarios/$uid/perfil_${UUID.randomUUID()}.jpg")

    referenciaImagen.putFile(uri)
        .addOnSuccessListener {
            referenciaImagen.downloadUrl.addOnSuccessListener { uri ->
                val db = FirebaseFirestore.getInstance()
                db.collection("usuarios").document(uid)
                    .update("urlImagenPerfil", uri.toString())
            }
        }
}