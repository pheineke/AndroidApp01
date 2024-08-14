package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyScreen()
        }
    }
}

val imageResources = listOf(
    R.drawable.meme01,
    R.drawable.meme02,
    R.drawable.meme03,
    R.drawable.meme04,
    R.drawable.meme05
)

@Composable
fun MyScreen() {
    var text by remember { mutableStateOf("") }
    var readText by remember { mutableStateOf("You didnt roll the dice yet") }
    var showImage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showImage) {
            var imageResource = imageResources.random()
            Image(
                painter = painterResource(
                    id = imageResource
                ),
                contentDescription = "My Image",
                modifier = Modifier.size(400.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Number of Sides") },
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            showImage = true
            if (text.isNotEmpty()) {
                try {
                    readText = RollDice(text.toInt()).toString()
                } catch (e: NumberFormatException) {
                    readText = "Invalid input"
                }

            }
        }) {
            Text("Roll Dice")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You rolled a ${readText}")

    }
}

fun RollDice(sides: Int): Int {
    return (1..sides).random()
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting(6)
    }
}

 */