package com.paywizzard.app.screens.authPages

import android.util.Log
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
            color = MaterialTheme.colorScheme.onSurface,
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


            EditText(
                value = emailValue,
                type = INPUT_TYPE.EMAIL,
                label = stringResource(id = R.string.email_address),
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

            EditText(
                value = passwordValue,
                type = INPUT_TYPE.EMAIL,
                label = stringResource(id = R.string.password),
                errorMessage = "Wrong password ",
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
                    style = MaterialTheme.typography.bodyMedium

                )
                Text(
                    modifier = Modifier.clickable { onSignUpClicked() },
                    text = "Sign up now",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge

                )
            }
        }
    }
}


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
                style =  MaterialTheme.typography.displayLarge
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
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        onSignIn()
                    }

                )
            }
        }
    }
}


@Composable
fun PersonalInfoPage(
    firstNameValue:String,
    lastNameValue: String,
    userNameValue:String,
    phoneNumberValue:String,
    refCodeValue: String = "",
    onFirstNameChanged:(String) -> Unit,
    onLastNameChanged:(String) -> Unit,
    onUserNameChanged:(String) -> Unit,
    onPhoneNumberChanged:(String) -> Unit,
    onRefCodeChanged:(String) -> Unit,
    onRegister:()->Unit

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
                .padding(top = 110.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .background(color = Color.Transparent),
            verticalArrangement = Arrangement.Center,

            ) {
            // Add your login UI components here

            Text(
                text = "Personal Info",
                color = MaterialTheme.colorScheme.primary,
                style =  MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(25.dp))


            Spacer(modifier = Modifier.height(30.dp))
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState(), true)
                    .background(color = Color.Transparent)
                    .padding(top = 40.dp, bottom = 20.dp),

                verticalArrangement = Arrangement.Center,
            ){



                EditText(
                    value = firstNameValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.firstName),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onFirstNameChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                EditText(
                    value = lastNameValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.lastName),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onLastNameChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                EditText(
                    value = phoneNumberValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.phone_number),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                       onPhoneNumberChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                EditText(
                    value = userNameValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.userName),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onUserNameChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                EditText(
                    value = refCodeValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.refCode),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    onValueChanged = {
                        onRefCodeChanged(it)
                    }
                )
                Spacer(modifier = Modifier.height(30.dp))
                BlueButton(
                    title = stringResource(R.string.next),
                    modifier = Modifier.padding(bottom = 50.dp)
                ) {
                    onRegister()
                }
                Spacer(modifier = Modifier.height(50.dp))
            }

        }

    }
}


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

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
        ){


            Text(
                text = stringResource(id = R.string.forgotPassword),
                color = MaterialTheme.colorScheme.primary,
                style =  MaterialTheme.typography.displayLarge,
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

/*
Preview section
 */
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
        RegisterPage(
            emailValue = "",
            passwordValue = "",
            cPasswordValue ="" ,
            onEmailChanged ={} ,
            onPasswordChanged = {},
            onConfirmPasswordChanged ={},
            onSignIn = {},

        ) {
            // on next page
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PersonalInfoPreview(){
    PAYWIZZARDTheme {
      PersonalInfoPage(
          firstNameValue = "",
          lastNameValue = "",
          userNameValue = "",
          phoneNumberValue = "",
          onFirstNameChanged ={} ,
          onLastNameChanged = {},
          onUserNameChanged = {},
          onPhoneNumberChanged = {},
          onRefCodeChanged = {}
      ) {
          
      }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgotPasswordPreview(){
    PAYWIZZARDTheme {
        var emailValue by remember { mutableStateOf("") }
      ForgotPasswordPage(emailValue = emailValue, onEmailChanged ={
          emailValue = it
      } ) {
          if (emailValue.contains("@")){
              Log.d("TAG","Submitted...")
          }else{
              Log.d("TAG","Wrong email")
          }

      }
    }
}