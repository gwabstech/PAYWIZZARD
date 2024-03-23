package com.paywizzard.app.nav

import com.paywizzard.app.R

sealed class BottomNavDestinations(
    val route: String,
    val title: String? = null,
    val imageID: Int,


    ) {

    data object HomeScreen : BottomNavDestinations(
        route = "HomeScreen",
        imageID = R.drawable.logo,
        title = "Home",

        )

    data object Wallet : BottomNavDestinations(
        route = "Wallet",
        imageID = R.drawable.balance_wallet,
        title = "Wallet",

        )

    data object History : BottomNavDestinations(
        route = "History",
        imageID = R.drawable.history,
        title = "History",

        )

    data object Account : BottomNavDestinations(
        route = "Account",
        imageID = R.drawable.baseline_account_circle_24,
        title = "Account",
        )


}

sealed class TopAppBarNavDestinations(
    val route: String,
) {
    data object MoreScreen : TopAppBarNavDestinations(route = "MoreScreen")
    data object NotificationScreen : TopAppBarNavDestinations(route = "NotificationScreen")

}
sealed class AuthDestination(val route: String){
    data object GetStartedScreen:AuthDestination(route = "GetStartedScreen")
    data object ForgotPasswordScreen:AuthDestination(route = "ForgotPasswordScreen")
    data object LoginScreen:AuthDestination(route = "LoginScreen")
    data object PersonalInfoScreen:AuthDestination(route = "PersonalInfoScreen")
    data object RegisterScreen:AuthDestination(route = "RegisterScreen")
}
sealed class HomeScreenNavDestinations(
    val route: String,
) {
    data object GiftScreen : HomeScreenNavDestinations(route = "GiftScreen")
    data object TransferScreen : HomeScreenNavDestinations(route = "TransferScreen")
    data object FundWalletScreen : HomeScreenNavDestinations(route = "FundWalletScreen")
    data object PromoBannerScreen : HomeScreenNavDestinations(route = "PromoBannerScreen")
    data object ReferAndEarnScreen : HomeScreenNavDestinations(route = "ReferAndEarnScreen")
    data object AirtimeSwapScreen : HomeScreenNavDestinations(route = "AirtimeSwapScreen")
    data object TransactionsScreen : HomeScreenNavDestinations(route = "TransactionsScreen")

}

sealed class ServicesDestinations(

    val route: String,
    val title: String? = null,
    val imageID: Int,
) {

    data object BuyAirtimeScreen : ServicesDestinations(
        route = "BuyAirtimeScreen", title = "Airtime", imageID = R.drawable.airtime
    )

    data object BuyDataScreen : ServicesDestinations(
        route = "BuyDataScreen", title = "Data", imageID = R.drawable.data
    )

    data object ElectricityScreen : ServicesDestinations(
        route = "ElectricityScreen", title = "Electricity", imageID = R.drawable.electric
    )

    data object BuyCableTVScreen : ServicesDestinations(
        route = "BuyCableTVScreen", title = "Cable TV", imageID = R.drawable.cable
    )

   data object AirTimeSwapScreen : ServicesDestinations(
        route = "AirTimeSwapScreen", title = "Airtime Swap", imageID = R.drawable.airtime_swap
    )

   data object EducationScreen : ServicesDestinations(
        route = "EducationScreen", title = "Education", imageID = R.drawable.baseline_school_24
    )

   data object BuyInternetScreen : ServicesDestinations(
        route = "Internet", title = "Internet", imageID = R.drawable.internet
    )

   data object MoreScreen : ServicesDestinations(
        route = "MoreScreen", title = "More", imageID = R.drawable.more
    )
}