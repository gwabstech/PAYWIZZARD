package com.paywizzard.app.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun OnboardingScreen1(){

}
@Composable
fun OnboardingScreen2(){

}
@Composable
fun OnboardingScreen3(){

}

@Preview(showBackground = true, showSystemUi = true,)
@Composable
fun OnboardingPreview() {
    PAYWIZZARDTheme (darkTheme = false){
      OnboardingScreen1()
    }
}