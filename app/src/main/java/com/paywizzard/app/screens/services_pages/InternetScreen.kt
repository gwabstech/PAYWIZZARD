package com.paywizzard.app.screens.services_pages
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.paywizzard.app.nav.ServicesDestinations

@Composable
fun BuyInternetScreen(
    navController: NavHostController,
    paddingValues: PaddingValues
){

    Column (
        modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){


        Text(text = "Buy Internet sub ",
            color = MaterialTheme.colorScheme.primary
        )

    }

}




