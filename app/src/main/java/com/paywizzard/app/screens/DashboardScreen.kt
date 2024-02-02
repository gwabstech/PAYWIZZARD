package com.paywizzard.app.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paywizzard.app.R
import com.paywizzard.app.components.BottomAppBar
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun DashboardPage() {
    Scaffold(
        topBar = { TopAppBar {

        } },
        bottomBar = { BottomAppBar() }
    ) {
        Dashboard(it)
    }
}

@Composable
fun Dashboard(
    modifier: PaddingValues
) {

}

@Composable
fun TopAppBar(
    onNotificationCLicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 8.dp, end = 8.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(end = 5.dp)

        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.back_button),
                contentScale = ContentScale.FillWidth,

                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                textAlign = TextAlign.Start,
                text = "@Paywizzard",
                modifier = Modifier.padding(horizontal = 5.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )

        }

        Icon(
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    //on clicked
                    onNotificationCLicked()
                },
            imageVector = Icons.Default.Notifications,
            tint = Color.Black,
            contentDescription = "Notification"
        )

    }

}


@Preview(showBackground = true)
@Composable
private fun GetStartedPreview() {
    PAYWIZZARDTheme {
       TopAppBar {

       }
    }
}