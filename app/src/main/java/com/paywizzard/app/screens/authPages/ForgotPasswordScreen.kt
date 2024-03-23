package com.paywizzard.app.screens.authPages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.R
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EmailTextField
import com.paywizzard.app.components.LoadingBottomSheetContent
import com.paywizzard.app.components.validateEmail
import com.paywizzard.app.data.models.ForgetPasswordState
import com.paywizzard.app.data.viewModels.AuthViewModel
import com.paywizzard.app.nav.AuthDestination
import com.paywizzard.app.network.RetrofitClient
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordPage(
    authViewModel: AuthViewModel,

    navController: NavHostController,


){

    var emailValue by remember {
        mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    val forgetPasswordState by authViewModel.forgotPasswordState.collectAsState()
    var isLoadingSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }


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
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
                .verticalScroll(rememberScrollState(), true),
            verticalArrangement = Arrangement.Top,
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

            EmailTextField(email = emailValue) {
                emailValue = it
            }

            Spacer(modifier = Modifier.height(40.dp))

            BlueButton(title = stringResource(R.string.submit)) {
                if (validateEmail(emailValue)) {
                    isLoadingSheetOpen = true
                    authViewModel.forgotPassword(emailValue)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (isLoadingSheetOpen) {
                HandleForgotPassword(
                    state = forgetPasswordState,
                    sheetState = sheetState,
                    navController = navController
                ) {
                    isLoadingSheetOpen = false
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleForgotPassword(
    state: ForgetPasswordState,
    sheetState: SheetState,
    navController: NavHostController,
    onDismissed:()-> Unit,



) {
    when (state) {
        is ForgetPasswordState.Loading ->{
            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {

               LoadingBottomSheetContent(message = "Sending forget password link..", isError = false) {
                   onDismissed()
               }
            }
        }
        is ForgetPasswordState.Success -> {
            Log.i("TAG", state.message)
            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {
                LoadingBottomSheetContent(message = state.message, isError = true) {
                    onDismissed()
                }
            }
            // TODO: fix it 

            LaunchedEffect(true) {
                delay(3000L) // 3-second delay
                navController.navigate(AuthDestination.LoginScreen.route)
            }
        }

        is ForgetPasswordState.Error -> {
            // Display an error message using Toast
            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {
                LoadingBottomSheetContent(message = state.message, isError = true) {
                    onDismissed()
                }
            }

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgotPasswordPreview(){
    PAYWIZZARDTheme {

        val navController = rememberNavController()
        val authViewModel = AuthViewModel(RetrofitClient.apiService())
        ForgotPasswordPage(authViewModel = authViewModel, navController = navController)
    }
}