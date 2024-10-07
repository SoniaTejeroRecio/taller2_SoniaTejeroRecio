package com.example.individual

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.individual.ui.theme.IndividualTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var userName by remember { mutableStateOf("") }
    var savedName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0) }
    var buttonColor by remember { mutableStateOf(Color.Blue) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val imageRes = when {
        currentHour in 6..11 -> R.drawable.sol // Buenos días
        currentHour in 12..17 -> R.drawable.libro // Buenas tardes
        else -> R.drawable.luna // Buenas noches
    }

    val backgroundImage: ImageBitmap = ImageBitmap.imageResource(id = imageRes)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            bitmap = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Cambiado a CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .border(4.dp, Color.Gray, RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontFamily = FontFamily.Cursive,
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (userName.isNotEmpty()) {
                            savedName = userName // Guarda el nombre ingresado
                            userName = ""
                            buttonColor = Color.Red
                        }
                    },
                    modifier = Modifier
                        .border(2.dp, buttonColor, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    Text("Guardar nombre", fontWeight = FontWeight.Bold)
                }

                // TextView que muestra el nombre guardado
                Text(
                    text = "Nombre guardado: $savedName",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para iniciar tarea en segundo plano
                Button(
                    onClick = {
                        isLoading = true
                        progress = 0 // Reiniciar progreso

                        GlobalScope.launch {
                            for (i in 1..100) {
                                Thread.sleep(50) // Simula tiempo de red
                                progress = i // Actualiza el progreso en el hilo principal
                            }
                            isLoading = false // Termina el loading cuando el progreso es 100
                        }
                    },
                    modifier = Modifier
                        .border(2.dp, buttonColor, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    Text("Iniciar tarea en segundo plano", fontWeight = FontWeight.Bold)
                }

                // Mostrar ProgressBar
                if (isLoading) {
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(
                        progress = progress / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = "Progreso: $progress%", fontSize = 20.sp, color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .border(2.dp, buttonColor, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Text("Mostrar usuarios", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Navegar a ThirdActivity
                    val intent = Intent(context, ThirdActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .border(2.dp, buttonColor, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Text("Ir a la tercera pantalla", fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Usuarios guardados") },
            text = {
                if (savedName.isEmpty()) {
                    Text("No se han introducido usuarios")
                } else {
                    Column {
                        Text(savedName) // Solo muestra el último nombre guardado
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    IndividualTheme {
        MainScreen()
    }
}
