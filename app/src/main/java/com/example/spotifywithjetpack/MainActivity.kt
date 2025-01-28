package com.example.spotifywithjetpack
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.spotifywithjetpack._core.nav.AppNavHost
import com.example.spotifywithjetpack.ui.theme.SpotifyWithJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyWithJetpackTheme {
                    AppNavHost()
            }
        }
    }
}



