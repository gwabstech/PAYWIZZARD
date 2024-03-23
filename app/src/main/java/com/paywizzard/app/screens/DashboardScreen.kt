package com.paywizzard.app.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.components.BottomAppBar
import com.paywizzard.app.nav.BottomNavDestinations
import com.paywizzard.app.nav.HomeScreenNavDestinations
import com.paywizzard.app.nav.ServicesDestinations
import com.paywizzard.app.nav.TopAppBarNavDestinations
import com.paywizzard.app.screens.services_pages.FundWalletScreen
import com.paywizzard.app.screens.services_pages.GiftScreen
import com.paywizzard.app.screens.services_pages.TransferScreen
import com.paywizzard.app.screens.account.ProfileScreen
import com.paywizzard.app.screens.services_pages.AirtimeSwapScreen
import com.paywizzard.app.screens.services_pages.BuyAirtimeScreen
import com.paywizzard.app.screens.services_pages.BuyCableTVScreen
import com.paywizzard.app.screens.services_pages.BuyDataScreen
import com.paywizzard.app.screens.services_pages.BuyInternetScreen
import com.paywizzard.app.screens.services_pages.EducationScreen
import com.paywizzard.app.screens.services_pages.ElectricityScreen
import com.paywizzard.app.screens.services_pages.PromoBannerScreen
import com.paywizzard.app.screens.services_pages.ReferAndEarnScreen
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun DashboardPage(
) {
    val navController = rememberNavController()

    val bottomNavigationScreens = listOf<BottomNavDestinations>(
        BottomNavDestinations.HomeScreen,
        BottomNavDestinations.Wallet,
        BottomNavDestinations.History,
        BottomNavDestinations.Account
    )
    PAYWIZZARDTheme {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            bottomBar = {
                BottomAppBar(navController,bottomNavigationScreens)
            }
        ) {
            MainScreenNavigationConfigurations(paddingValues = it, navController = navController)

        }
    }

}


@Composable
private fun MainScreenNavigationConfigurations(
    paddingValues: PaddingValues,
    navController: NavHostController,
    ) {

    NavHost(navController, startDestination = BottomNavDestinations.HomeScreen.route) {


        composable(BottomNavDestinations.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(BottomNavDestinations.Wallet.route){
            WalletScreen(paddingValues,navController = navController)
        }
        composable(BottomNavDestinations.History.route){
            HistoryScreen(paddingValues,navController = navController)
        }
        composable(BottomNavDestinations.Account.route){
            ProfileScreen(paddingValues,navController = navController)
        }

        composable(TopAppBarNavDestinations.NotificationScreen.route) {
            // Destination implementation
            NotificationScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(TopAppBarNavDestinations.MoreScreen.route) {
            // Destination implementation
           MoreScreen(paddingValues = paddingValues, navController = navController)
        }

        composable(HomeScreenNavDestinations.GiftScreen.route) {
            GiftScreen(navController = navController, paddingValues = paddingValues )
        }
        composable(HomeScreenNavDestinations.TransferScreen.route) {
            TransferScreen(navController = navController, paddingValues = paddingValues )
        }

        composable(HomeScreenNavDestinations.FundWalletScreen.route) {
            FundWalletScreen(navController = navController, paddingValues = paddingValues )
        }


        composable(HomeScreenNavDestinations.AirtimeSwapScreen.route) {
            AirtimeSwapScreen(navController = navController, paddingValues = paddingValues )
        }
        composable(HomeScreenNavDestinations.PromoBannerScreen.route) {
            PromoBannerScreen(navController = navController, paddingValues = paddingValues )
        }

        composable(HomeScreenNavDestinations.ReferAndEarnScreen.route) {
            ReferAndEarnScreen(navController = navController, paddingValues = paddingValues )
        }
        composable(HomeScreenNavDestinations.TransactionsScreen.route) {
           HistoryScreen(paddingValues = paddingValues, navController = navController)
        }
        // here

        composable(ServicesDestinations.BuyAirtimeScreen.route) {
            BuyAirtimeScreen(navController = navController, paddingValues = paddingValues )
        }
        composable(ServicesDestinations.BuyDataScreen.route) {
            BuyDataScreen(navController = navController, paddingValues = paddingValues )
        }

        composable(ServicesDestinations.ElectricityScreen.route) {
            ElectricityScreen(navController = navController, paddingValues = paddingValues )
        }

        composable(ServicesDestinations.BuyCableTVScreen.route) {
            BuyCableTVScreen(navController = navController, paddingValues = paddingValues )
        }
        composable(ServicesDestinations.EducationScreen.route) {
            EducationScreen(navController = navController, paddingValues = paddingValues )
        }

        composable(ServicesDestinations.BuyInternetScreen.route) {
            BuyInternetScreen(navController = navController, paddingValues = paddingValues )
        }
        composable(ServicesDestinations.AirTimeSwapScreen.route) {
            AirtimeSwapScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(ServicesDestinations.MoreScreen.route) {
            MoreScreen(paddingValues = paddingValues, navController = navController)
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DashBoardPreview() {
    PAYWIZZARDTheme {
        DashboardPage()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePagePreview() {
    PAYWIZZARDTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}