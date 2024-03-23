package com.paywizzard.app.components

import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paywizzard.app.ui.theme.lightBlue


@Composable
fun BlueButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    ElevatedButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        ),

        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)

    ) {
        Text(
            text = title,
            style = TextStyle(
                fontStyle = FontStyle(com.paywizzard.app.R.font.poppins_bold),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineBreak = LineBreak.Simple,
                textAlign = TextAlign.Center,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.CenterVertically)

        )
    }
}


@Composable
fun WhiteButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(18),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontStyle = FontStyle(com.paywizzard.app.R.font.poppins_bold),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineBreak = LineBreak.Simple,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),


        )
    }
}


