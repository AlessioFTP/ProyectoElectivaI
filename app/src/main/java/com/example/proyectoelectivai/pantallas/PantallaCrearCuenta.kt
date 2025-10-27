package com.example.proyectoelectivai.pantallas


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel

@Composable
fun PantallaCrearCuenta(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: AutenticarViewModel = viewModel()
    val mensaje by viewModel.mensaje.collectAsState()

    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }

    LaunchedEffect(mensaje) {
        if (mensaje == "Registro exitoso") {
            navController.navigate(PantallasApp.PantallaPerfil.ruta) {
                popUpTo(PantallasApp.PantallaCrearCuenta.ruta) { inclusive = true }
            }
        }
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
                .padding(vertical = 40.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.alex),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(230.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Text(
                text = "Correo",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 4.dp)
            )

            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Ingrese su correo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)
            )


            Text(
                text = "Usuario",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 4.dp)
            )

            TextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Ingrese su usuario") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    ),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Text(
                text = "Contraseña",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 4.dp)
            )

            TextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text("Ingrese su contraseña") },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.registrarUsuario(correo, clave, usuario) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Crear Cuenta",
                    fontSize = 20.dp.value.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            if (mensaje != null) {
                Spacer(modifier = Modifier.height(8.dp))
            } else {
                Spacer(modifier = Modifier.height(20.dp))
            }

            mensaje?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 15.dp.value.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }

            Text(
                text = "¿Ya tienes cuenta creada?",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(PantallasApp.PantallaInicioSesion.ruta) {
                            popUpTo(PantallasApp.PantallaCrearCuenta.ruta) { inclusive = true }
                        }
                    },
                fontSize = 15.dp.value.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }

}