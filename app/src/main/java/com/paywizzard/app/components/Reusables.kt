package com.paywizzard.app.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.paywizzard.app.R
import com.paywizzard.app.data.Transaction
import com.paywizzard.app.data.TransactionType
import com.paywizzard.app.nav.BottomNavDestinations
import com.paywizzard.app.nav.HomeScreenNavDestinations
import com.paywizzard.app.nav.TopAppBarNavDestinations
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

enum class INPUT_TYPE {
    PASSWORD,
    EMAIL,
    ORDERS,
}


@Composable
fun TransactionItemCard(transaction: Transaction, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set container background to white
            contentColor = Color.White // Use theme's onBackground color for text
        ),// Set background color
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // Handle card click here
                           navController.navigate(HomeScreenNavDestinations.TransactionsScreen.route)
                },
            verticalArrangement = Arrangement.Center, // Center elements vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center elements horizontally
        ) {



            val amountColor = if (transaction.type == TransactionType.WALLET_TOP_UP) {
                Color.Green
            } else {
                Color.Red
            }


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Text(
                    text = transaction.title,
                    fontWeight = FontWeight.Bold,
                    // Adjust font size
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                    // Set text color
                )

                Text(
                    text = "₦ ${transaction.amount}",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = amountColor,
                    style = MaterialTheme.typography.titleSmall,

                    )
            }


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = "DateTime: ${transaction.dateAndTime}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black // Set text color
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Reference: ${transaction.tRef}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )

            Spacer(
                modifier = Modifier.height(1.dp)
                    .fillMaxWidth().background(Color.Black)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BannerCard(navController: NavHostController) {

    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        painterResource(id = R.drawable.banner1),
        painterResource(id = R.drawable.banner2),
        painterResource(id = R.drawable.banner3)
    )

    // LaunchedEffect to automatically scroll (optional)
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column {
        HorizontalPager(
            count = imageSlider.size,
            state = pagerState,
            modifier = Modifier
                .height(114.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray.copy(alpha = 0.2f), // Set container background to white
                    contentColor = MaterialTheme.colorScheme.onBackground // Use theme's onBackground color for text
                ),
                // Pass the image index and navigation controller to Image
                onClick = { bannerOnClick(page, navController) } // New onClick handler
            ) {
                Image(
                    painter = imageSlider[page],
                    contentDescription = "Banner",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

// Function to handle image click on Banner
fun bannerOnClick(pageIndex: Int, navController: NavHostController) {
    // Access the clicked image resource ID using imageSlider[pageIndex]
    // Perform your desired action with the image and index:
    // - Log the clicked image details
    // - Navigate to a new screen with the image information
    // - Show an info dialog about the image
    // Example: navigate to a detail screen with the image index
    val context = LocalContext
    when(pageIndex){
        0 -> navController.navigate(HomeScreenNavDestinations.ReferAndEarnScreen.route)
        1 -> navController.navigate(HomeScreenNavDestinations.AirtimeSwapScreen.route)
        2 -> navController.navigate(HomeScreenNavDestinations.PromoBannerScreen.route)

    }
    Log.i("TAG","the image at $pageIndex")
    // navController.navigate("imageDetail/$pageIndex") // Assuming you have a 'imageDetail' route
}

@Composable
fun BottomAppBar(
    navController: NavController,
    bottomNavigationScreens: List<BottomNavDestinations>,


) {

    NavigationBar(
        containerColor = Color.White,  // Set background color to white
        // Set content color to white
        tonalElevation = 10.dp,
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        )
    ) {
        // Navigation items with labels and icons
        // (Adjust based on your specific items)


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        bottomNavigationScreens.forEach { screens ->


            if (screens == BottomNavDestinations.HomeScreen){
                NavigationBarItem(
                    selected = currentRoute == screens.route, // Assuming the first item is selected initially
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        if (currentRoute != screens.route) {
                            navController.navigate(screens.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                           Image(
                               modifier = Modifier.size(30.dp),
                               painter = painterResource(id = R.drawable.logo),
                               contentDescription = "",

                           )
                    },
                    label = {
                        Text(
                            screens.title.toString(),
                            color = MaterialTheme.colorScheme.scrim,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                )
            } else{
                NavigationBarItem(
                    selected = currentRoute == screens.route, // Assuming the first item is selected initially
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        if (currentRoute != screens.route) {
                            navController.navigate(screens.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painterResource(id = screens.imageID),
                            contentDescription = screens.title,
                            tint = MaterialTheme.colorScheme.primary
                        )


                    },
                    label = {
                        Text(
                            screens.title.toString(),
                            color = MaterialTheme.colorScheme.scrim,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                )
            }

        }


    }
}
    @Composable
    fun TopAppBar(
        navController: NavHostController,
        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(end = 5.dp)

            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(R.string.back_button),
                    contentScale = ContentScale.FillWidth,

                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = "Paywizzard",
                    modifier = Modifier.padding(horizontal = 5.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    modifier = Modifier
                        .clickable {
                            //on clicked
                            navController.navigate(TopAppBarNavDestinations.NotificationScreen.route)
                        },
                    painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                    tint = Color.Black,
                    contentDescription = "Notification"
                )

                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    modifier = Modifier

                        .clickable {
                            //on clicked
                            navController.navigate(TopAppBarNavDestinations.MoreScreen.route)
                        },
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    tint = Color.Black,
                    contentDescription = "MoreScreen"
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }



@Composable
fun GeneraleTopAppBar(
    title: String = "Airtime",
    onBackArrowClick: () -> Unit,
    onHistoryClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackArrowClick() }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.back_button),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )
            }
            TextButton(onClick = { onHistoryClick() }) {
                Text(
                    textAlign = TextAlign.Center,
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }

        TextButton(onClick = { onHistoryClick() }) {
            Text(
                text = stringResource(R.string.history),
                style = MaterialTheme.typography.displayLarge
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    type: INPUT_TYPE,
    label: String,
    errorMessage: String,
    keyboardOptions: KeyboardOptions,
    onValueChanged: (String) -> Unit,
) {
    var isValid by remember { mutableStateOf(true) }

    val visualTransformation = if (type == INPUT_TYPE.PASSWORD) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        keyboardOptions = keyboardOptions,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        onValueChange = {
            onValueChanged(it)

            isValid = validateInput(type, it)
        },
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xffD9D9D9),
            unfocusedBorderColor = Color(0x56020040), //
        ),
        modifier = modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.displayMedium
    )

    if (!isValid) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .basicMarquee(),
            text = getErrorMessage(type),
            color = Color.Red,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.End,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    paddingValues: PaddingValues,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.padding(paddingValues),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Text(text = "BottomSheet")
    }
}

// Add your validation logic here
fun validateInput(type: INPUT_TYPE, input: String): Boolean {
    // Implement your validation logic based on the input type
    // For example, check if it's a valid email, password, etc.
    return true
}

// Add your error message logic here
fun getErrorMessage(type: INPUT_TYPE): String {
    // Implement your error message logic based on the input type
    // For example, return a different error message for invalid email, password, etc.
    return "Invalid input"
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GeneraleTopAppBarPreview() {
    PAYWIZZARDTheme {
        GeneraleTopAppBar(title = "Airtime", onBackArrowClick = { /*TODO*/ }) {

        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TopAppBarPreview() {

    PAYWIZZARDTheme {
        val navController = rememberNavController()

        TopAppBar(navController = navController)




    }
}

@Preview(showBackground = true)
@Composable
private fun ButtomAppBarPreview() {
    PAYWIZZARDTheme {
        val navController = rememberNavController()
        val bottomNavigationScreens = listOf<BottomNavDestinations>(
            BottomNavDestinations.HomeScreen,
            BottomNavDestinations.Wallet,
            BottomNavDestinations.History,
            BottomNavDestinations.Account
        )

        BottomAppBar(navController, bottomNavigationScreens)


    }
}