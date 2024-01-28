package com.paywizzard.app.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paywizzard.app.R
import com.paywizzard.app.data.OnboardingPageData
import com.paywizzard.app.data.onboardingPages
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme


@Composable
fun OnboardingScreen(
    toAuthActivity: ()-> Unit
) {
    var currentPage by remember { mutableIntStateOf(0) }

    // Function to handle "Next" button click


    val onNextClick: () -> Unit = {
        if (currentPage < onboardingPages.size - 1) {
            currentPage++
        } else {
            toAuthActivity()
        }
    }

    // Function to handle "Skip" button click
    val onSkipClick: () -> Unit = {
        //Todo navigation to LoginScreen
        // Handle skip action, e.g., navigate to the main screen
        toAuthActivity()
    }

    // Call the OnboardingPage composable with the current page data
    OnboardingPage(
        modifier = Modifier.fillMaxSize(),
        imageId = onboardingPages[currentPage].imageId,
        title = onboardingPages[currentPage].title,
        description = onboardingPages[currentPage].description,
        isLastPage = onboardingPages[currentPage].isLastPage,
        onNextClick = onNextClick,
        onSkipClick = onSkipClick
    )
}

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    description: String,
    isLastPage: Boolean,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))
        // Skip text (top-left corner)
        if (!isLastPage) {
            Text(
                text = "Skip",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onSkipClick() }
                    .align(Alignment.End),
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(imageId),
            contentDescription = "Onboarding background image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        // Title
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = description,
            style = TextStyle(
                fontStyle = FontStyle(R.font.poppins_regular),
                fontSize = 14.sp,
                letterSpacing = 2.sp,
                lineBreak = LineBreak.Simple,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Buttons
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.BottomCenter
        ) {

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
            ) {
                Text(
                    text = if (isLastPage) "Get Started" else "Next",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = modifier.height(10.dp))

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {

    PAYWIZZARDTheme(darkTheme = false) {

        OnboardingScreen(){

        }


    }


}