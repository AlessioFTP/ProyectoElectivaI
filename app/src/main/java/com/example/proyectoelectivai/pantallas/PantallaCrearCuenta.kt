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
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.navegacion.PantallasApp

@Composable
fun PantallaCrearCuenta(navController: NavController, modifier: Modifier = Modifier) {
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
            Text(
                text = "BIENVENIDO",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = Bold,
                fontSize = 50.dp.value.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.alex),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(350.dp)
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

            var texto3 by remember { mutableStateOf("") }

            TextField(
                value = texto3,
                onValueChange = { texto3 = it },
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

            var texto by remember { mutableStateOf("") }

            TextField(
                value = texto,
                onValueChange = { texto = it },
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

            var texto2 by remember { mutableStateOf("") }

            TextField(
                value = texto2,
                onValueChange = { texto2 = it },
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
                onClick = {/**/ },
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¿Ya tienes cuenta creada?",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(PantallasApp.PantallaInicioSesion.ruta) },
                fontSize = 15.dp.value.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }

}