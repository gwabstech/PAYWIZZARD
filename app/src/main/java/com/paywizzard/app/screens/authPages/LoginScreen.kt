@file:OptIn(ExperimentalMaterial3Api::class)

package com.paywizzard.app.screens.authPages

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.R
import com.paywizzard.app.activities.AuthActivity
import com.paywizzard.app.activities.DashBoardActivity
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EmailTextField
import com.paywizzard.app.components.INPUT_TYPE
import com.paywizzard.app.components.LoadingBottomSheetContent
import com.paywizzard.app.components.PasswordTextField
import com.paywizzard.app.components.ShowError
import com.paywizzard.app.components.validateEmail
import com.paywizzard.app.components.validatePassword
import com.paywizzard.app.data.models.LoginState
import com.paywizzard.app.data.viewModels.AuthViewModel
import com.paywizzard.app.nav.AuthDestination
import com.paywizzard.app.network.RetrofitClient
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFormPage(
    authViewModel: AuthViewModel,

    navController: NavHostController,
    onLoggedIn :(String)->Unit,

    ) {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }


    var showEmailValueError by remember {
        mutableStateOf(false)
    }

    var showPasswordError by remember {
        mutableStateOf(false)
    }

    var emailIsValid by remember {
        mutableStateOf(false)
    }
    var passwordIsValid by remember {
        mutableStateOf(false)
    }


    val loginState by authViewModel.loginState.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var isLoadingSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    // var showIndicator by remember {mutableStateOf(false)}

    Column(
        modifier = Modifier
            .padding(13.dp)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.size(65.dp).align(Alignment.End)
                .padding(end = 13.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), true),
            verticalArrangement = Arrangement.Center,
            ) {
            // Add your login UI components here
            Text(
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Hello\nSign in with your registered email ",
                textAlign = TextAlign.Start,
                style  = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_medium),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    letterSpacing = 2.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Start,
                )

            )

            Spacer(modifier = Modifier.height(30.dp))


            EmailTextField(email = emailValue) {
                emailValue = it
                emailIsValid = validateEmail(it)
            }

            Spacer(modifier = Modifier.height(20.dp))


            PasswordTextField(password = passwordValue) {
                passwordValue = it
                passwordIsValid = validatePassword(it)
            }

            Text(
                modifier = Modifier
                    .padding(top = 10.dp, end = 5.dp)
                    .align(Alignment.End)
                    .clickable { navController.navigate(AuthDestination.ForgotPasswordScreen.route) },
                text = "Forgot Password",
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_medium),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.End,
                )

            )


            Spacer(modifier = Modifier.height(20.dp))

            BlueButton(title = stringResource(id = R.string.login)) {


                if (!validateEmail(emailValue)) {

                    Log.i("TAG","email error")
                }else if (!validatePassword(passwordValue)){

                    Log.i("TAG","email error")
                }else{
                    showEmailValueError = false
                    showPasswordError = false
                    isLoadingSheetOpen = true
                    authViewModel.login(emailValue, passwordValue)
                }

            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an Account?",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontStyle = FontStyle(R.font.poppins_medium),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        letterSpacing = 2.sp,
                        lineBreak = LineBreak.Simple,
                        textAlign = TextAlign.Start,
                    )

                )

                Text(
                    text = " Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable {  navController.navigate(AuthDestination.RegisterScreen.route) },
                    style = TextStyle(
                        fontStyle = FontStyle(R.font.poppins_medium),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        lineBreak = LineBreak.Simple,
                        textAlign = TextAlign.Start,
                    )

                )

            }

            if (isLoadingSheetOpen){
                HandleLogin(loginState = loginState, sheetState =sheetState , onDismissed = {isLoadingSheetOpen = false}){
                    onLoggedIn(it)
                }
            }
        }
    }
        // Add your image as the background

        // Column for your login UI


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleLogin(
    loginState: LoginState,
    sheetState: SheetState,
    onDismissed:()-> Unit,
    onLoggedIn: (String) -> Unit


) {
    when (loginState) {
        is LoginState.Loading ->{
            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {
               
                LoadingBottomSheetContent(message = "Logging in...", isError = false) {
                    onDismissed()
                }
            }
        }
        is LoginState.Success -> {
            // Display your success message using Toast
            Log.i("TAG",loginState.token)
           onLoggedIn(loginState.token)
        }

        is LoginState.Error -> {
            // Display an error message using Toast
           Log.i("TAG",loginState.message)

            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {
               LoadingBottomSheetContent(message = loginState.message, isError = true) {
                   onDismissed()
               }
            }

        }
    }
}


/*
Preview section
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPreview() {
    PAYWIZZARDTheme {

        val authViewModel = AuthViewModel(RetrofitClient.apiService())
        val context = LocalContext.current
        val navController = rememberNavController()

        LoginFormPage(authViewModel = authViewModel, navController = navController){

        }


    }
}