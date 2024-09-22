package com.example.individual

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.individual.ui.theme.IndividualTheme
import java.util.*

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualTheme {
                StartScreen()
            }
        }
    }
}

@Composable
fun StartScreen() {
    val greeting = getGreeting()
    val context = LocalContext.current

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF3F51B5), Color(0xFF2196F3), Color(0xFFFFC107))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = greeting,
                fontFamily = FontFamily.Cursive,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    // Navegar a MainActivity
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .border(2.dp, Color.White, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Text("Ir a la actividad principal", fontWeight = FontWeight.Bold)
            }
        }
    }
}

fun getGreeting(): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (currentHour) {
        in 6..11 -> "Buenos días"
        in 12..17 -> "Buenas tardes"
        else -> "Buenas noches"
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    IndividualTheme {
        StartScreen()
    }
}