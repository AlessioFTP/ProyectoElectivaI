package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectoelectivai.datos.modelo.Bloque
import com.example.proyectoelectivai.R


@Composable
fun BloqueEditor(
    bloque: Bloque,
    onChange: (Bloque) -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF26323A))

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Tipo: ${bloque.tipo}",
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp)
            )
            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(R.drawable.icono_borrar),
                    contentDescription = "Eliminar bloque",
                    tint = Color.Red
                )
            }
        }

        if (bloque.tipo == "texto") {
            OutlinedTextField(
                value = bloque.titulo,
                onValueChange = { onChange(bloque.copy(titulo = it)) },
                label = { Text("Título") },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            )
            OutlinedTextField(
                value = bloque.valor,
                onValueChange = { onChange(bloque.copy(valor = it)) },
                label = { Text("Contenido") },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            )
        } else if (bloque.tipo == "imagen") {
            OutlinedTextField(
                value = bloque.valor,
                onValueChange = { onChange(bloque.copy(valor = it)) },
                label = { Text("URL de imagen") },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            )
        }

        Spacer(Modifier.height(6.dp))
    }
}
