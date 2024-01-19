package com.paywizzard.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paywizzard.app.R
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Spacer(modifier = modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Give more space to the top section
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = modifier
                    .width(100.dp)
                    .height(100.dp) // Adjust size as needed
            )
        }

        // Bottom section with image and button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp), // Add some padding around the elements
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Spacer(modifier = modifier.width(2.dp))

               Text(
                   text = stringResource(R.string.appName),
                   color = MaterialTheme.colorScheme.primary,
                   style = MaterialTheme.typography.displayMedium,
                   letterSpacing = 2.sp

               )


            }



            Text(
                text = stringResource(R.string.appVersion),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.padding(10.dp)

            )
            Spacer(modifier = modifier.height(10.dp))

        }
    }
}



@Preview(showBackground = true, showSystemUi = true,)
@Composable
fun SplashScreenPreview() {
    PAYWIZZARDTheme (darkTheme = false){

    }
}