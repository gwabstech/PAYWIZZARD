package com.paywizzard.app.data

import androidx.compose.ui.res.stringResource
import com.paywizzard.app.R


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

data class OnboardingPageData(
    val imageId: Int,
    val title: String,
    val description: String,
    val isLastPage: Boolean
)

// Sample onboarding pages data
val onboardingPages = listOf(
    OnboardingPageData(
        imageId = R.drawable.onboarding_image_1,
        title = "Paywizzad",
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