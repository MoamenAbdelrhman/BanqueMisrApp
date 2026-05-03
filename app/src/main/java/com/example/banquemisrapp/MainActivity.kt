package com.example.banquemisrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.banquemisrapp.ui.theme.BanqueMisrAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager.init(this)
        enableEdgeToEdge()
        setContent {
            BanqueMisrAppTheme {
                Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                BanqueMisrApp()
            }
            }

            }
        }
    }
@Composable
fun BanqueMisrApp() {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BanqueMisrAppTheme {
        LoginScreen()
    }
}