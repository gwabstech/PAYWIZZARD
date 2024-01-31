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
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
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
            containerColor = lightBlue
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.background
        )
    }
}


