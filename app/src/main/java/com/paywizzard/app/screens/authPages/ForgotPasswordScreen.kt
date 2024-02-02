package com.paywizzard.app.screens.authPages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paywizzard.app.R
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EditText
import com.paywizzard.app.components.INPUT_TYPE
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun ForgotPasswordPage(
    emailValue: String,
    onEmailChanged: (String) -> Unit,
    onSubmit: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Add your image as the background
        Image(
            painter = painterResource(id = R.drawable.login_background), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.forgotPassword),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Enter your registered email ",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(30.dp))
            EditText(
                value = emailValue,
                type = INPUT_TYPE.EMAIL,
                label = "Email",
                errorMessage = "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    onEmailChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            BlueButton(title = stringResource(R.string.submit)) {
                onSubmit()
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgotPasswordPreview(){
    PAYWIZZARDTheme {
        var emailValue by remember { mutableStateOf("") }
        ForgotPasswordPage(emailValue = emailValue, onEmailChanged = {
            emailValue = it
        }) {
            if (emailValue.contains("@")) {
                Log.d("TAG", "Submitted...")
            } else {
                Log.d("TAG", "Wrong email")
            }

        }
    }
}