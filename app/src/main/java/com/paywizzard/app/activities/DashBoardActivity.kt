package com.paywizzard.app.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.activities.ui.theme.PAYWIZZARDTheme
import com.paywizzard.app.screens.DashboardPage
import com.paywizzard.app.screens.HomeScreen

class DashBoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAYWIZZARDTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DashboardPage()
                }
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DashBoardPreview() {
    com.paywizzard.app.ui.theme.PAYWIZZARDTheme {
        val navController = rememberNavController()
       DashboardPage()
    }
}