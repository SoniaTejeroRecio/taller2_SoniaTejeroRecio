package com.example.individual

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.individual.ui.theme.IndividualTheme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualTheme {
                ThirdScreen()
            }
        }
    }
}

@Composable
fun ThirdScreen() {
    var backgroundColor by remember { mutableStateOf(Color.White) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seleccione el color de fondo",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { backgroundColor = Color.Green },
                    modifier = Modifier
                        .border(2.dp, Color.Green, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    Text("Verde", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { backgroundColor = Color.Blue },
                    modifier = Modifier
                        .border(2.dp, Color.Blue, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    Text("Azul", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { backgroundColor = Color.Red },
                    modifier = Modifier
                        .border(2.dp, Color.Red, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    Text("Rojo", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Navegar a StartActivity
                    val intent = Intent(context, StartActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Text("Volver a la pantalla de inicio", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    IndividualTheme {
        ThirdScreen()
    }
}