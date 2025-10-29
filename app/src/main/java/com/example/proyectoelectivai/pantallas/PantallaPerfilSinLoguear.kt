package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
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
fun PantallaPerfilSinLoguear(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xFF161516)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
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

            Button(
                onClick = {navController.navigate(PantallasApp.PantallaInicioSesion.ruta)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(
                    text = "Iniciar Sesón",
                    fontSize = 20.dp.value.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {navController.navigate(PantallasApp.PantallaCrearCuenta.ruta)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(
                    text = "Crear Cuenta",
                    fontSize = 20.dp.value.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "He olvidado mi contraseña",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* */ },
                fontSize = 15.dp.value.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }

}


