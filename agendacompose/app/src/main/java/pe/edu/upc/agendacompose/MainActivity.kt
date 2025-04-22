package pe.edu.upc.agendacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pe.edu.upc.agendacompose.presentation.ui.Home
import pe.edu.upc.agendacompose.presentation.ui.theme.AgendaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgendaComposeTheme {
               Home()
            }
        }
    }
}

