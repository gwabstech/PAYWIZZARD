package com.paywizzard.app.nav

import com.paywizzard.app.R

sealed class BottomNavDestinations(
    val route: String,
    val title: String? = null,
    val imageID: Int,


    ) {

    object HomeScreen : BottomNavDestinations(
        route = "HomeScreen",
        imageID = R.drawable.logo,
        title = "Home",

        )

    object Wallet : BottomNavDestinations(
        route = "Wallet",
        imageID = R.drawable.balance_wallet,
        title = "Wallet",

        )

    object History : BottomNavDestinations(
        route = "History",
        imageID = R.drawable.history,
        title = "History",

        )

    object Account : BottomNavDestinations(
        route = "Account",
        imageID = R.drawable.baseline_account_circle_24,
        title = "Account",

        )


}

sealed class TopAppBarNavDestinations(
    val route: String,
) {
    object MoreScreen : TopAppBarNavDestinations(route = "MoreScreen")
    object NotificationScreen : TopAppBarNavDestinations(route = "NotificationScreen")




}

sealed class HomeScreenNavDestinations(
    val route: String,
) {
    object GiftScreen : HomeScreenNavDestinations(route = "GiftScreen")
    object TransferScreen : HomeScreenNavDestinations(route = "TransferScreen")
    object FundWalletScreen : HomeScreenNavDestinations(route = "FundWalletScreen")
    object PromoBannerScreen : HomeScreenNavDestinations(route = "PromoBannerScreen")
    object ReferAndEarnScreen : HomeScreenNavDestinations(route = "ReferAndEarnScreen")
    object AirtimeSwapScreen : HomeScreenNavDestinations(route = "AirtimeSwapScreen")
    object TransactionsScreen : HomeScreenNavDestinations(route = "TransactionsScreen")

}

sealed class ServicesDestinations(

    val route: String,
    val title: String? = null,
    val imageID: Int,
) {

    object BuyAirtimeScreen : ServicesDestinations(
        route = "BuyAirtimeScreen", title = "Airtime", imageID = R.drawable.airtime
    )

    object BuyDataScreen : ServicesDestinations(
        route = "BuyDataScreen", title = "Data", imageID = R.drawable.data
    )

    object ElectricityScreen : ServicesDestinations(
        route = "ElectricityScreen", title = "Electricity", imageID = R.drawable.electric
    )

    object BuyCableTVScreen : ServicesDestinations(
        route = "BuyCableTVScreen", title = "Cable TV", imageID = R.drawable.cable
    )

    object AirTimeSwapScreen : ServicesDestinations(
        route = "AirTimeSwapScreen", title = "AirSwap", imageID = R.drawable.airtime_swap
    )

    object EducationScreen : ServicesDestinations(
        route = "EducationScreen", title = "Education", imageID = R.drawable.baseline_school_24
    )

    object BuyInternetScreen : ServicesDestinations(
        route = "Internet", title = "Internet", imageID = R.drawable.internet
    )

    object MoreScreen : ServicesDestinations(
        route = "MoreScreen", title = "More", imageID = R.drawable.more
    )
}