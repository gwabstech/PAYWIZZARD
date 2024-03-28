package com.paywizzard.app.activities

import android.app.Activity
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.components.registerNetworkCallback
import com.paywizzard.app.components.unregisterNetworkCallback
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
    private val networkCallback: com.paywizzard.app.components.NetworkCallbackImpl = com.paywizzard.app.components.NetworkCallbackImpl()
    private val loginViewModel: AuthViewModel by viewModels {
        AuthViewModel.Factory(RetrofitClient.apiService())
    }
    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModel.Factory(RetrofitClient.apiService())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerNetworkCallback(this, networkCallback)
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

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkCallback(this, networkCallback)
    }

    private inner class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            // Device connected to a network with internet capabilities
            // Update UI or perform actions that require internet access
            Log.i("TAG","Connected to the internet!")
            Toast.makeText(this@AuthActivity, "Connected to the internet!", Toast.LENGTH_SHORT).show()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            // Device lost connection to the network
            // Handle offline behavior
            Log.i("TAG","Disconnected from the internet!")
            Toast.makeText(this@AuthActivity, "Disconnected from the internet!", Toast.LENGTH_SHORT).show()
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