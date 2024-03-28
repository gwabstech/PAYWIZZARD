package com.paywizzard.app.screens.authPages

import android.util.Log
import androidx.collection.emptyIntFloatMap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.R
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EmailTextField
import com.paywizzard.app.components.INPUT_TYPE
import com.paywizzard.app.components.PasswordTextField
import com.paywizzard.app.components.ShowError
import com.paywizzard.app.components.getErrorMessage
import com.paywizzard.app.components.validateCP
import com.paywizzard.app.components.validateEmail
import com.paywizzard.app.components.validateInput
import com.paywizzard.app.components.validatePassword
import com.paywizzard.app.data.viewModels.RegisterViewModel
import com.paywizzard.app.nav.AuthDestination
import com.paywizzard.app.network.RetrofitClient
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterPage(
    registerViewModel: RegisterViewModel,
    navController: NavHostController,

    ) {

    var emailValue by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    var rePassword by remember {
        mutableStateOf("")
    }

    var showEmailValueError by remember {
        mutableStateOf(false)
    }

    var showPasswordError by remember {
        mutableStateOf(false)
    }
    var showRePasswordError by remember {
        mutableStateOf(false)
    }



    // Column for your login UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .size(65.dp)
                .padding(end = 13.dp)
                .align(Alignment.End)
        )
        // Column for your login UI
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), true),
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


            EmailTextField(email = emailValue) {
                emailValue = it
                registerViewModel.setEmail(it)

            }
            if (showEmailValueError)
                ShowError(type =INPUT_TYPE.EMAIL,emailValue, password,rePassword )


            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(password = password) {

                password = it
                registerViewModel.setPassword(password)
            }
            if (showPasswordError)
                ShowError(type =INPUT_TYPE.PASSWORD, emailValue,password,rePassword )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                password = rePassword,
                label = "Re Password"
            ) {
                rePassword = it
                registerViewModel.setConfirmPassword(rePassword)
            }
            if (showRePasswordError)
                ShowError(type =INPUT_TYPE.CPASSWORD,emailValue,password,rePassword )

            Text(
                modifier = Modifier
                    .basicMarquee(
                        iterations = Int.MAX_VALUE
                    )
                    .padding(10.dp),
                text = "Password should consist of at least 8 characters with a mix of uppercase letters (A-Z), lowercase letters (a-z), and numbers (0-9).",
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_medium),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 3.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                ),

                )
            Spacer(modifier = Modifier.height(20.dp))
            BlueButton(title = stringResource(R.string.next)) {

                if (!validateEmail(emailValue)) {
                    showEmailValueError = true
                } else if (!validatePassword(password)){
                    showPasswordError = true
                }else if (!validateCP(password,rePassword)) {
                    showRePasswordError = true
                } else{
                    Log.i("TAG","AM clicked")
                    navController.navigate(AuthDestination.PersonalInfoScreen.route)
                }


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
                    fontSize = 15.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(AuthDestination.LoginScreen.route)
                    }

                )
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterPreview() {
    PAYWIZZARDTheme {

        val registerViewModel = RegisterViewModel(RetrofitClient.apiService())
        registerViewModel.setEmail("")
        registerViewModel.setPassword("")
        registerViewModel.setConfirmPassword("")
       // val context = AuthActivity::getApplicationContext.name
        val navController = rememberNavController()

        RegisterPage(registerViewModel = registerViewModel, navController = navController)

    }
}