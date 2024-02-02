package com.paywizzard.app.screens.authPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paywizzard.app.R
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.WhiteButton
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun GetStartedPage(
    onLoginCLicked: () -> Unit,
    onRegisterClicked: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GetStartedPreview(){
    PAYWIZZARDTheme {

        GetStartedPage(onLoginCLicked = {

        }) {

        }


    }
}