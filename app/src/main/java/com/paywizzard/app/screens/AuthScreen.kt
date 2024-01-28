package com.paywizzard.app.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.paywizzard.app.components.WhiteButton
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun GetStartedPage(
    onLoginCLicked: () -> Unit,
    onRegisterClicked: () -> Unit
){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Adjust size as needed
        )


        Spacer(modifier = Modifier.height(10.dp))
        // Title
        Text(
            text = stringResource(id = R.string.appName),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge

            )

        Spacer(modifier = Modifier.height(30.dp))

        BlueButton(title = stringResource(id = R.string.login)) {
            onLoginCLicked()
        }
        Spacer(modifier = Modifier.height(30.dp))

        WhiteButton(title = stringResource(id = R.string.register)) {
            onRegisterClicked()
        }

    }


}

@Composable
fun LoginPage(
    emailValue: String,
    passwordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onForgotPassClicked: () -> Unit,
    onSignUpClicked:()-> Unit,
    onLogin: () -> Unit
) {
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
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colorScheme.primary,
                style =  MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Hello\nSign in with your registered email ",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium

            )

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Email Address",
                style = MaterialTheme.typography.displayMedium
            )

            EditText(
                value = emailValue,
                type = INPUT_TYPE.EMAIL,
                label = "",
                errorMessage = "Invalid Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                   onEmailChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "password",
                style = MaterialTheme.typography.displayMedium
            )
            EditText(
                value = passwordValue,
                type = INPUT_TYPE.EMAIL,
                label = "",
                errorMessage = "Invalid Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                onValueChanged = {
                    onPasswordChanged(it)
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable { onForgotPassClicked() },
                text = "Forgot Password",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(20.dp))

            BlueButton(title = stringResource(id = R.string.login)) {
                onLogin()
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Don't have an Account? ",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge

                )
                Text(
                    modifier = Modifier.clickable { onSignUpClicked() },
                    text = "Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge

                )
            }
        }
    }
}


@Composable
fun Register(
    emailValue: String,

    passwordValue: String,
    cPasswordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
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
                style =  MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(25.dp))


            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.email_address),
                style = MaterialTheme.typography.displayMedium
            )

            EditText(
                value = emailValue,
                type = INPUT_TYPE.EMAIL,
                label = "",
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

            Text(
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.displayMedium
            )
            EditText(
                value = passwordValue,
                type = INPUT_TYPE.PASSWORD,
                label = "",
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



            Text(
                text = stringResource(R.string.re_password),
                style = MaterialTheme.typography.displayMedium
            )
            EditText(
                value = cPasswordValue,
                type = INPUT_TYPE.PASSWORD,
                label = "",
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
            BlueButton(title = stringResource(R.string.next)) {
               onNext()
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge

                )
                Text(
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge

                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPreview(){
    PAYWIZZARDTheme {

        LoginPage(
            emailValue = "",
            passwordValue = "",
            onEmailChanged = {},
            onForgotPassClicked = {},
            onPasswordChanged = {},
            onSignUpClicked = {}
        ) {

        }
        /*
        GetStartedPage(onLoginCLicked = { /*TODO*/ }) {
            // on Register


        }

         */
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GetStartedPreview(){
    PAYWIZZARDTheme {



        GetStartedPage(onLoginCLicked = {

        }) {

        }


    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterPreview(){
    PAYWIZZARDTheme {

        Register(
            emailValue = "",
            passwordValue = "",
            cPasswordValue ="" ,
            onEmailChanged ={} ,
            onPasswordChanged = {},
            onConfirmPasswordChanged ={},

        ) {
            // on next page
        }

    }
}