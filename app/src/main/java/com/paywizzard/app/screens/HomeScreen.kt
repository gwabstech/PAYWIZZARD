package com.paywizzard.app.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.accompanist.pager.ExperimentalPagerApi
import com.paywizzard.app.R
import com.paywizzard.app.components.BannerCard
import com.paywizzard.app.components.FundWalletButtomSheetContent
import com.paywizzard.app.components.ServiceItemCard
import com.paywizzard.app.components.TopAppBar
import com.paywizzard.app.components.TransactionItemCard
import com.paywizzard.app.data.Transaction
import com.paywizzard.app.data.TransactionType
import com.paywizzard.app.data.transactionList
import com.paywizzard.app.nav.HomeScreenNavDestinations
import com.paywizzard.app.nav.ServicesDestinations
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {

    val sheetState = rememberModalBottomSheetState()
    var isFundWalletSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    MaterialTheme {
        Surface (
          color = MaterialTheme.colorScheme.background
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                TopAppBar(navController)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                        .verticalScroll(rememberScrollState()),
                ) {

                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp),
                        text = "Hi Favour ",
                        style = TextStyle(
                            fontStyle = FontStyle(R.font.poppins_medium),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            letterSpacing = 3.sp,
                            lineBreak = LineBreak.Simple,
                            textAlign = TextAlign.Start,
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    BalanceAndDashboard(navController = navController){
                        isFundWalletSheetOpen = true
                    }

                    Text(
                        modifier = Modifier
                            .basicMarquee(
                                iterations = Int.MAX_VALUE
                            )
                            .padding(10.dp),
                        text = "Hi Favour Airtime Swap and gifting is now available kindly check them out.... thank you for patronage, bills payments made easy ",
                        style = TextStyle(
                            fontStyle = FontStyle(R.font.poppins_medium),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 3.sp,
                            lineBreak = LineBreak.Simple,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        ),

                        )
                    ServiceCard(navController = navController)

                    Spacer(modifier = Modifier.height(15.dp))
                    BannerCard(navController = navController)

                    RecentTransactions(navController = navController, transactionList())

                    if (isFundWalletSheetOpen){
                        ModalBottomSheet(
                            sheetState = sheetState,
                            dragHandle = { BottomSheetDefaults.DragHandle() },
                            onDismissRequest = { isFundWalletSheetOpen = false }
                        ) {
                            var account = "12378463456"

                            FundWalletButtomSheetContent(navController = navController,
                                onBankTransferClicked = { /*TODO*/ },
                                onManualFundingClicked = {}
                            ) {
                                // on Monify cliked
                            }

                        }
                    }
                }



            }
        }
    }


}

@Composable
private fun RecentTransactions(
    navController: NavHostController,
    transactions : List<Transaction>
){

    Column (
          horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.background)

    ){

        if (transactions.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 30.dp)// Set a specific height for LazyColumn
            ) {

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ){
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){

                            Text(
                                text = "Recent Transactions",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.Black
                            )
                            TextButton(onClick = { navController.navigate(HomeScreenNavDestinations.TransactionsScreen.route) }) {
                                Text(text = "Show all")
                            }
                        }

                        NoTransactionAvailable()
                    }

            }
        }else{

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(bottom = 30.dp)// Set a specific height for LazyColumn
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    item {
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){

                            Text(
                                text = "Recent Transactions",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.Black
                            )
                            TextButton(onClick = { navController.navigate(HomeScreenNavDestinations.TransactionsScreen.route) }) {
                                Text(text = "Show all")
                            }
                        }
                    }

                    items(transactions) { transaction ->
                       TransactionItemCard(transaction = transaction,navController)

                    }
                }

            }
        }


        Spacer(modifier = Modifier.height(50.dp))

    }
}



@Composable
private fun BalanceAndDashboard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onFundWallet: () -> Unit
) {

    var displayBalance by remember {
        mutableStateOf("₦ 500,000.00")
    }
    var exposeBalance by remember {
        mutableStateOf(false)
    }
    Column(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Available balance",
                color = Color.Black,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                )
            )



            Icon(
                painter = painterResource(id = R.drawable.red_eye_24),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        exposeBalance = !exposeBalance
                    }
                    .size(20.dp)

            )
        }




        if (exposeBalance) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = displayBalance,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                )
            )
        } else {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                text = "xxxxxxxx",
                style = TextStyle(
                    color = Color.Black,
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Spacer(modifier = modifier.height(20.dp))
       ActionsBanner(
           onFundWallet = { onFundWallet()},
           onTransfer = { navController.navigate(HomeScreenNavDestinations.TransferScreen.route)}) {
           navController.navigate(HomeScreenNavDestinations.GiftScreen.route)
       }
    }

}

@Composable
fun NoTransactionAvailable() {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = rememberAsyncImagePainter(R.drawable.no_transaction, imageLoader),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.noTransactions),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
        )
    }
}
@Composable
private fun ActionsBanner(
    onFundWallet:()->Unit,
    onTransfer:()-> Unit,
    onGift:() -> Unit,
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.outline_add_circle_24),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .size(20.dp)

            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                modifier = Modifier
                    .clickable {
                       onFundWallet()
                    },
                text = "Fund Wallet",
                color = Color.White,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 12.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.baseline_send_24),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .size(20.dp)

            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                modifier = Modifier
                    .clickable { onTransfer() },
                text = "Transfer",
                color = Color.White,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.baseline_card_giftcard_24),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .size(20.dp)

            )
            Spacer(modifier = Modifier.width(5.dp))

            Text(
                modifier = Modifier
                    .clickable { onGift() },
                text = "Gifting",
                color = Color.White,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_regular),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 2.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                )
            )
        }


    }


}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ServiceCard(navController: NavHostController) {

    val services = listOf<ServicesDestinations>(
        ServicesDestinations.BuyAirtimeScreen,
        ServicesDestinations.EducationScreen,
        ServicesDestinations.BuyDataScreen,
        ServicesDestinations.BuyInternetScreen,
        ServicesDestinations.ElectricityScreen,
        ServicesDestinations.AirTimeSwapScreen,
        ServicesDestinations.BuyCableTVScreen,
        ServicesDestinations.MoreScreen,
    )

    val pagerState = rememberLazyGridState() // State for horizontal pager
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        // Set specific colors for container and content
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background, // Set container background to white
            contentColor = MaterialTheme.colorScheme.onBackground // Use theme's onBackground color for text
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column {
            Text(
                text = "Services",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                // Set text color explicitly if needed (optional)
                color = MaterialTheme.colorScheme.background,
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Center,
                ),
                fontWeight = FontWeight.Bold
            )

                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 20.dp, end = 20.dp),
                    state = pagerState,
                    rows = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(services) { service ->
                        ServiceItemCard(servicesDestinations = service,navController)
                    }
                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                val activeColor = MaterialTheme.colorScheme.primary // Customize as needed
                val inactiveColor = Color.Black.copy(alpha = 0.6f) // Customize as needed

                repeat (2) {
                    val isSelected = pagerState.isScrollInProgress
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) activeColor else inactiveColor)
                            .padding(horizontal = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }


        }
    }

}


@Preview(showBackground = true,)
@Composable
private fun HomePagePreview() {
    PAYWIZZARDTheme {
         val navController = rememberNavController()
         HomeScreen(navController)
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionPreview() {
    PAYWIZZARDTheme {
        val navController = rememberNavController()
        val transaction1 = Transaction(
            title = "Airtime",
            amount = 200.00,
            dateAndTime = "2024-02-13 12:30 PM",
            type = TransactionType.AIRTIME,
            tRef = "TRX123456"
        )
        TransactionItemCard(transaction = transaction1,navController)
    }
}
