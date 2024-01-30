package com.paywizzard.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.paywizzard.app.R

val Poppins = FontFamily(
    Font(R.font.poppins_bold),
    Font(R.font.poppins_regular),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_light),
    Font(R.font.poppins_black_italic),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font( R.font.poppins_medium)),
        fontWeight = FontWeight.Normal,
        letterSpacing = 2.sp,
        fontSize = 20.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontWeight = FontWeight.Thin,
        textAlign = TextAlign.Center,
        fontSize = 14.sp
    ),
    bodyMedium =  TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_light)) ,
        fontWeight = FontWeight.Thin,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

)