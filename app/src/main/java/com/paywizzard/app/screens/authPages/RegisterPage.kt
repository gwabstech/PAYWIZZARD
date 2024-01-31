package com.paywizzard.app.screens.authPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun RegisterPage(
    emailValue: String,
    passwordValue: String,
    cPasswordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onSignIn:()-> Unit,
    onNext: () -> Unit,

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

        // Column for your login UI
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), true)
                .background(color = Color.Transparent),
            verticalArrangement = Arrangement.Center,

            ) {
            // Add your login UI components here

            Text(
                text = "Sign up",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(25.dp))


            Spacer(modifier = Modifier.height(30.dp))


            EditText(
                value = emailValue,
                type = INPUT_TYPE.EMAIL,
                label = stringResource(id = R.string.email_address),
                errorMessage = "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    onEmailChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditText(
                value = passwordValue,
                type = INPUT_TYPE.PASSWORD,
                label = stringResource(id = R.string.password),
                errorMessage = "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onValueChanged = {
                    onPasswordChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditText(
                value = cPasswordValue,
                type = INPUT_TYPE.PASSWORD,
                label = stringResource(id = R.string.re_password),
                errorMessage = "",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onValueChanged = {
                    onConfirmPasswordChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            BlueButton(title = stringResource(R.string.next)) {
                onNext()
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge

                )
                Text(
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        onSignIn()
                    }

                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterPreview(){
    PAYWIZZARDTheme {
        RegisterPage(
            emailValue = "",
            passwordValue = "",
            cPasswordValue = "",
            onEmailChanged = {},
            onPasswordChanged = {},
            onConfirmPasswordChanged = {},
            onSignIn = {},

            ) {
            // on next page
        }

    }
}