package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.focus.onFocusChanged


@Composable
fun BarraBusqueda(
    query: String,
    onQueryChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
        placeholder = { Text("Buscar") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color.Blue.copy(alpha = 0.5f))
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White.copy(alpha = 0.7f),
            cursorColor = Color.Black
        )
    )
}
