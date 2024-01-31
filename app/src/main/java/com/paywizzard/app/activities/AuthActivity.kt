package com.paywizzard.app.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.paywizzard.app.screens.authPages.GetStartedPage
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAYWIZZARDTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    GetStartedPage(onLoginCLicked = {
                        Toast.makeText(this,"Login",Toast.LENGTH_SHORT).show()
                    }) {
                        Toast.makeText(this,"Register",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PAYWIZZARDTheme {
        GetStartedPage(onLoginCLicked = { /*TODO*/ }) {

        }
    }
}