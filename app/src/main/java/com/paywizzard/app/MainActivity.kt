package com.paywizzard.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.paywizzard.app.data.OnboardingPageData
import com.paywizzard.app.screens.OnboardingPage
import com.paywizzard.app.screens.OnboardingScreen
import com.paywizzard.app.screens.SplashScreen
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAYWIZZARDTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                 OnboardingScreen {
                     val intent = Intent(this, AuthActivity::class.java)
                     startActivity(intent)
                     this.finish()
                 }
                }

            }
        }
    }
}


/*
@Composable
fun HomePage(

){

    var currentPage by remember { mutableIntStateOf(0) }
    val data1 = """
        Get ready to revolutionize the way you manage your utility bills. Sign up, link your accounts,and effortlessly pay your bills with just a few taps. Add cash for future payments and withdraw anytime you need.
         It's that simple! Let's make bill management a breeze together.
    """.trimIndent()
    val data2 = """
        You're in control of your utility bills like never before. Add cash to your wallet for stress-free payments, and if you ever need to withdraw, it's just a click away. Start your journey to hassle-free bill management now!
    """.trimIndent()

    val data3 = """
        Say goodbye to bill payment stress!Top up your wallet for future payments and withdraw at your convenience. Let's simplify your bill payments together!
    """.trimIndent()

    // Sample onboarding pages data
    val onboardingPages = listOf(
        OnboardingPageData(
            imageId = R.drawable.onboarding_image_1,
            title = stringResource(id = R.string.appName),
            description = data1,
            isLastPage = false
        ),
        OnboardingPageData(
            imageId = R.drawable.onboarding_image2,
            title = "Explore Features",
            description = data2,
            isLastPage = false
        ),
        OnboardingPageData(
            imageId = R.drawable.onboarding_image3,
            title = "Get Started Now",
            description = data3,
            isLastPage = true
        )
    )

    // Function to handle "Next" button click
    val onNextClick: () -> Unit = {
        if (currentPage < onboardingPages.size - 1) {
            currentPage++
        } else {
            // Handle completion, e.g., navigate to the main screen
        }
    }

    // Function to handle "Skip" button click
    val onSkipClick: () -> Unit = {
        // Handle skip action, e.g., navigate to the main screen
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

 */

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    PAYWIZZARDTheme {
        OnboardingScreen {

        }
    }
}