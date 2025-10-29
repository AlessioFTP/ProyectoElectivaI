package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.proyectoelectivai.datos.modelo.Apartado
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.datos.modelo.Bloque

@Composable
fun ApartadoEditor(
    apartado: Apartado,
    onChange: (Apartado) -> Unit,
    onDelete: () -> Unit
) {
    var titulo by remember { mutableStateOf(apartado.titulo) }
    var bloques by remember { mutableStateOf(apartado.contenido.toMutableStateList()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF18232B))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            OutlinedTextField(
                value = titulo,
                onValueChange = {
                    titulo = it
                    onChange(apartado.copy(titulo = titulo, contenido = bloques))
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                label = { Text("Título del apartado") },
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(R.drawable.icono_borrar),
                    contentDescription = "Eliminar apartado",
                    tint = Color.Red
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        bloques.forEachIndexed { index, bloque ->
            BloqueEditor(
                bloque = bloque,
                onChange = { nuevo ->
                    val nuevosBloques = bloques.toMutableStateList()
                    nuevosBloques[index] = nuevo
                    bloques = nuevosBloques
                    onChange(apartado.copy(titulo = titulo, contenido = nuevosBloques))
                },
                onDelete = {
                    val nuevosBloques = bloques.toMutableStateList()
                    nuevosBloques.removeAt(index)
                    bloques = nuevosBloques
                    onChange(apartado.copy(titulo = titulo, contenido = nuevosBloques))
                }
            )
        }

        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    bloques.add(Bloque(tipo = "texto", titulo = "", valor = ""))
                    onChange(apartado.copy(titulo = titulo, contenido = bloques))
                },
            ) {
                Text("Agregar Texto")
            }
            Button(
                onClick = {
                    bloques.add(Bloque(tipo = "imagen", titulo = "", valor = ""))
                    onChange(apartado.copy(titulo = titulo, contenido = bloques))
                },
            ) {
                Text("Agregar Imagen")
            }
        }


    }

}