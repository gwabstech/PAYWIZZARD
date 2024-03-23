package com.paywizzard.app.activities

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.data.viewModels.AuthViewModel
import com.paywizzard.app.data.viewModels.RegisterViewModel
import com.paywizzard.app.nav.AuthDestination
import com.paywizzard.app.network.RetrofitClient
import com.paywizzard.app.screens.authPages.ForgotPasswordPage
import com.paywizzard.app.screens.authPages.GetStartedPage
import com.paywizzard.app.screens.authPages.LoginFormPage
import com.paywizzard.app.screens.authPages.PersonalInfoPage
import com.paywizzard.app.screens.authPages.RegisterPage
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

class AuthActivity : ComponentActivity() {
    //val authViewModel = AuthViewModel(RetrofitClient.apiService())
    //  val registerViewModel = RegisterViewModel(RetrofitClient.apiService())
    private val loginViewModel: AuthViewModel by viewModels {
        AuthViewModel.Factory(RetrofitClient.apiService())
    }
    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModel.Factory(RetrofitClient.apiService())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAYWIZZARDTheme(

            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    registerViewModel.initializeViewModelData()

                    AuthNavigationConfigurations(
                        navController = navController,
                        this@AuthActivity,
                        loginViewModel = loginViewModel,
                        registerViewModel = registerViewModel
                    )
                }
            }
        }
    }
}


@Composable
private fun AuthNavigationConfigurations(
    navController: NavHostController,
    context: Activity,
    loginViewModel: AuthViewModel,
    registerViewModel: RegisterViewModel
) {

    NavHost(navController, startDestination = AuthDestination.GetStartedScreen.route) {
        composable(AuthDestination.GetStartedScreen.route) {
            GetStartedPage(navController)
        }

        composable(AuthDestination.LoginScreen.route) {
            LoginFormPage(
                loginViewModel,
                onLoggedIn = {
                    val intent = Intent(context, DashBoardActivity::class.java)
                    context.startActivity(intent)
                    context.finish()
                },
                navController = navController

            )
        }

        composable(AuthDestination.ForgotPasswordScreen.route) {
            ForgotPasswordPage(authViewModel = loginViewModel, navController = navController)

        }

        composable(AuthDestination.RegisterScreen.route) {

            RegisterPage(registerViewModel = registerViewModel, navController = navController)
        }

        composable(AuthDestination.PersonalInfoScreen.route) {
            PersonalInfoPage(
                // context = context,
                registerViewModel = registerViewModel,
                navController = navController
            )
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthPreview() {
    PAYWIZZARDTheme {
        val navController = rememberNavController()
        GetStartedPage(navHostController = navController)
    }
}