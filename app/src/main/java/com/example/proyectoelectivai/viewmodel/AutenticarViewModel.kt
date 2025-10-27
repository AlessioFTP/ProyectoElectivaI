package com.example.proyectoelectivai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import android.util.Patterns

class AutenticarViewModel : ViewModel() {
    private val autenticar: FirebaseAuth = FirebaseAuth.getInstance()

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    private val _usuario = MutableStateFlow<String?>(null)

    val usuario: StateFlow<String?> = _usuario

    fun iniciarSesion(correo: String, clave: String) {
        viewModelScope.launch {
            if (!validarCampos(correo, clave)) {
                return@launch
            }
            try {
                autenticar.signInWithEmailAndPassword(correo, clave).await()
                _mensaje.value = "Inicio de sesión exitoso"
            } catch (e: Exception) {
                _mensaje.value = "Correo o clave incorrectos"
            }
        }

    }

    fun registrarUsuario(correo: String, clave: String, usuario: String) {
        viewModelScope.launch {
            if (!validarCampos(correo, clave, usuario)) {
                return@launch
            }
            if (!validarUsuarioUnico(usuario)) {
                _mensaje.value = "El nombre de usuario ya existe"
                return@launch
            }
            try {
                autenticar.createUserWithEmailAndPassword(correo, clave).await()
                val uid = autenticar.currentUser?.uid ?: return@launch
                guardarUsuarioEnFirestore(uid, usuario, correo)
                _mensaje.value = "Registro exitoso"
            } catch (e: Exception) {
                _mensaje.value = "Error en el registro"
            }
        }
    }

    fun validarCampos(
        correo: String,
        clave: String,
        usuario: String = "vacio"
    ): Boolean {
        if (correo.isBlank() || clave.isBlank() || usuario.isBlank()) {
            _mensaje.value = "Todos los campos son obligatorios"
            return false
        }
        if (clave.length < 6) {
            _mensaje.value = "La clave debe tener al menos 6 caracteres"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            _mensaje.value = "Ingrese un correo válido"
            return false
        }
        return true
    }

    fun guardarUsuarioEnFirestore(uid: String, usuario: String, correo: String) {
        val db = FirebaseFirestore.getInstance()
        val datosUsuario = hashMapOf(
            "usuario" to usuario,
            "correo" to correo,
            "uid" to uid
        )
        db.collection("usuarios")
            .document(uid)
            .set(datosUsuario)
    }

    suspend fun validarUsuarioUnico(usuario: String): Boolean {
        val db = FirebaseFirestore.getInstance()
        val resultado = db.collection("usuarios")
            .whereEqualTo("usuario", usuario)
            .get()
            .await()
        return resultado.isEmpty
    }

    fun cerrarSesion() {
        autenticar.signOut()
        _mensaje.value = "Sesión cerrada"
        _usuario.value = null
    }

    fun usuarioLogueado(): Boolean {
        return autenticar.currentUser != null
    }

    suspend fun obtenerUsuarioActual(): String? {
        if (!usuarioLogueado()) {
            return null
        }

        val correoActual = FirebaseAuth.getInstance().currentUser?.email ?: return null
        val db = FirebaseFirestore.getInstance()

        val resultado = db.collection("usuarios")
            .whereEqualTo("correo", correoActual)
            .get()
            .await()

        return if (resultado.documents.isNotEmpty()) {
            resultado.documents[0].getString("usuario")
        } else {
            null
        }
    }

    fun actualizarUsuario(nombre: String) {
        _usuario.value = nombre
    }

    fun obtenerUidActual(): String? {
        return autenticar.currentUser?.uid
    }

}