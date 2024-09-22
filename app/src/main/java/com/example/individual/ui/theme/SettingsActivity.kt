package com.example.individual

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.individual.ui.theme.IndividualTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualTheme {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    var backgroundColor by remember { mutableStateOf(Color.White) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Seleccione el color de fondo")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { backgroundColor = Color.Red }) {
                Text("Rojo")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { backgroundColor = Color.Green }) {
                Text("Verde")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { backgroundColor = Color.Blue }) {
                Text("Azul")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Navegar a StartActivity
            val intent = Intent(context, StartActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Volver a la pantalla de inicio")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    IndividualTheme {
        SettingsScreen()
    }
}