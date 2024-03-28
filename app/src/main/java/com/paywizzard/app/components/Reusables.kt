@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.paywizzard.app.components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.paywizzard.app.nav.ServicesDestinations
import com.paywizzard.app.nav.TopAppBarNavDestinations
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import java.util.regex.Pattern

enum class INPUT_TYPE {
    PASSWORD, EMAIL, PHONE, NUMBER, ORDERS, CPASSWORD,
}

enum class NETWORK {
    MTN, AIRTEL, GLO, NINE_MOBILE
}

enum class TRANSACTION_TYPE {
    AIRTIME, DATA, ELECTRICITY, INTERNET, WEAC, NECO, JAMB
}


enum class PaymentOption(val label: String) {
    WALLET(label = "Wallet"),
    MONNIFY("Monnify")
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
                }, verticalArrangement = Arrangement.Center, // Center elements vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center elements horizontally
        ) {


            val amountColor = if (transaction.type == TransactionType.WALLET_TOP_UP) {
                Color(0xFF39E212)
            } else {
                Color.Red
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = transaction.title, fontWeight = FontWeight.Bold,
                    // Adjust font size
                    color = Color.Black, style = MaterialTheme.typography.titleSmall
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
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        }
    }
}


@Composable
fun ServiceItemCard(servicesDestinations: ServicesDestinations, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(5.dp)
    ) {
        IconButton(onClick = {
            navController.navigate(servicesDestinations.route)
        }) {
            Icon(
                painter = painterResource(id = servicesDestinations.imageID),
                contentDescription = servicesDestinations.title,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = servicesDestinations.title!!,
            style = TextStyle(
                fontStyle = FontStyle(R.font.poppins_bold),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 2.sp,
                lineBreak = LineBreak.Simple,
                textAlign = TextAlign.Center,
            ),
            color = Color.Black,


            )
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
            Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background, // Set container background to white
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
    when (pageIndex) {
        0 -> navController.navigate(HomeScreenNavDestinations.ReferAndEarnScreen.route)
        1 -> navController.navigate(HomeScreenNavDestinations.AirtimeSwapScreen.route)
        2 -> navController.navigate(HomeScreenNavDestinations.PromoBannerScreen.route)

    }
    Log.i("TAG", "the image at $pageIndex")
    // navController.navigate("imageDetail/$pageIndex") // Assuming you have a 'imageDetail' route
}

@Composable
fun BottomAppBar(
    navController: NavController,
    bottomNavigationScreens: List<BottomNavDestinations>,


    ) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background, // Set container background to white
            contentColor = MaterialTheme.colorScheme.background // Use theme's onBackground color for text
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ), shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,  // Set background color to white
            // Set content color to white
            tonalElevation = 30.dp, modifier = Modifier.background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            )
        ) {
            // Navigation items with labels and icons
            // (Adjust based on your specific items)


            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route


            bottomNavigationScreens.forEach { screens ->


                if (screens == BottomNavDestinations.HomeScreen) {
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
                } else {
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
}

@Composable
fun TopAppBar(
    navController: NavHostController,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(25.dp)
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
                modifier = Modifier.clickable {
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
    title: String = "Airtime", onBackArrowClick: () -> Unit, onHistoryClick: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background, // Set container background to white
            contentColor = MaterialTheme.colorScheme.background // Use theme's onBackground color for text
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.primary),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onBackArrowClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                            tint = Color.White,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                        )
                    }

                    Text(
                        textAlign = TextAlign.Center,
                        text = title,
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle(R.font.poppins_bold),
                            fontSize = 15.sp,
                            letterSpacing = 2.sp,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Center
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                }

                TextButton(onClick = { onHistoryClick() }) {
                    Text(
                        text = stringResource(R.string.history),
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle(R.font.poppins_bold),
                            fontSize = 15.sp,
                            letterSpacing = 2.sp,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

            }

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

            isValid = validateInput(type, it, "",)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (String) -> Unit,
) {
    var isValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = email,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        label = {
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        onValueChange = {
            onEmailChanged(it)
            isValid = validateEmail(it)
        },
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xffD9D9D9),
            unfocusedBorderColor = Color(0x56020040),
        ),
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        visualTransformation = VisualTransformation.None,
        textStyle = MaterialTheme.typography.displayMedium
    )

    if (!isValid) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .basicMarquee(),
            text = getErrorMessage(INPUT_TYPE.EMAIL),
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    label: String = "Password",
    onPasswordChanged: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) } // Track password visibility
    var isValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = password,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        onValueChange = {
            onPasswordChanged(it)
            isValid = validatePassword(it)
        },
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xffD9D9D9),
            unfocusedBorderColor = Color(0x56020040),
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = if (isPasswordVisible) painterResource(id = R.drawable.visibility) else painterResource(
                        id = R.drawable.visibility_off
                    ), contentDescription = "Toggle password visibility"
                )
            }
        },
        textStyle = MaterialTheme.typography.displayMedium
    )

    if (!isValid) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .basicMarquee(),
            text = getErrorMessage(INPUT_TYPE.PASSWORD),
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            maxLines = 1
        )
    }
}


@Composable
fun LoadingBottomSheetContent(
    isError: Boolean,
    message: String,
    onDismissed: () -> Unit
) {

    var loadingMessage by remember {
        mutableStateOf(message)
    }

    if (!isError) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(250.dp)
                .padding(10.dp)
                .background(Color.White)
        ) {

            CircularProgressIndicator(
                modifier = Modifier
                    .padding(10.dp)
                    .size(45.dp),
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = loadingMessage,
                modifier = Modifier.padding(5.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 18.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.scrim
                )
            )
        }

    } else {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(250.dp)
                .padding(10.dp)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(45.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                text = loadingMessage,
                modifier = Modifier.padding(5.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.scrim
                ),
            )
            BlueButton(
                title = "Dismiss",
                modifier = Modifier.padding(10.dp)
            ) {
                onDismissed()
            }
        }
    }
}


@Composable
fun FundWalletButtomSheetContent(
    navController: NavHostController,
    onBankTransferClicked: () -> Unit,
    onManualFundingClicked: () -> Unit,
    onMonnifyClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Text(
            text = "Fund wallet",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(R.font.poppins_bold),
                fontSize = 16.sp,
                letterSpacing = 3.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            ),
            color = MaterialTheme.colorScheme.scrim,
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.scrim)
                .fillMaxWidth()
        )
        Text(
            text = "Secured Payment with monnify ",
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle(R.font.poppins_regular),
                fontSize = 15.sp,
                letterSpacing = 2.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            ),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Kan Bank Logo",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            TextButton(onClick = { onBankTransferClicked() }) {
                Text(
                    text = stringResource(R.string.bank_transfer),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(R.font.poppins_bold),
                        fontSize = 16.sp,
                        letterSpacing = 2.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.scrim)
                .fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Kan Bank Logo",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            TextButton(onClick = { onManualFundingClicked() }) {
                Text(
                    text = stringResource(R.string.manual_funding),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(R.font.poppins_bold),
                        fontSize = 16.sp,
                        letterSpacing = 2.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.scrim)
                .fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.monnify),
                contentDescription = "Kan Bank Logo",
                modifier = Modifier.size(30.dp),
            )
            TextButton(onClick = { onMonnifyClicked() }) {
                Text(
                    text = " Top up with Monnify",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(R.font.poppins_bold),
                        fontSize = 16.sp,
                        letterSpacing = 2.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.scrim)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Composable
fun ConfirmPurchaseDialogContentCard(
    paymentType: TRANSACTION_TYPE,
    amount: String,
    provider: String,
    mobileNumber: String,
    onConfirmPaymentClick: (PaymentOption) -> Unit

) {

    var selectedOption by remember { mutableStateOf(PaymentOption.WALLET) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "${paymentType.name} PURCHASE",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(R.font.poppins_bold),
                fontSize = 14.sp,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Start,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            text = "₦$amount",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(R.font.poppins_bold),
                fontSize = 14.sp,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Start,
                color = Color.Black
            ),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(5.dp))
        PaymentDetailsCard(amount = amount, provider = provider, mobileNumber = mobileNumber)
        Spacer(modifier = Modifier.height(5.dp))
        PaymentOptionsCard {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        BlueButton(title = "Confirm payment") {
            onConfirmPaymentClick(selectedOption)
        }
        Spacer(modifier = Modifier.height(30.dp))

    }
}


@Composable
fun PaymentOptionsCard(
    onSelectedOptionChanged: (PaymentOption) -> Unit
) {
    var selectedOption by remember { mutableStateOf(PaymentOption.WALLET) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background, // Set container background to white
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Text(
                text = "Secured Payment options ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(R.font.poppins_bold),
                    color = MaterialTheme.colorScheme.scrim,
                    fontSize = 15.sp,
                    letterSpacing = 2.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center
                ),
            )

            Spacer(modifier = Modifier.height(15.dp))
            // Wallet Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOption = PaymentOption.WALLET
                        onSelectedOptionChanged(selectedOption)
                    }
            ) {


                Icon(
                    painter = painterResource(id = R.drawable.balance_wallet),
                    contentDescription = "Wallet Icon",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Wallet",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(R.font.poppins_bold),
                        color = MaterialTheme.colorScheme.scrim,
                        fontSize = 13.sp,
                        letterSpacing = 0.30.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier.weight(1f)
                )
                RadioButton(
                    selected = selectedOption == PaymentOption.WALLET,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        selectedOption = PaymentOption.WALLET
                        onSelectedOptionChanged(selectedOption)
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            // Monnify Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOption = PaymentOption.MONNIFY
                        onSelectedOptionChanged(selectedOption)
                    }
            ) {


                Image(
                    painter = painterResource(id = R.drawable.monnify),
                    contentDescription = "Monnify Logo",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Monnify",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(R.font.poppins_bold),
                        color = MaterialTheme.colorScheme.scrim,
                        fontSize = 13.sp,
                        letterSpacing = 0.30.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier.weight(1f)
                )

                RadioButton(
                    selected = selectedOption == PaymentOption.MONNIFY,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        selectedOption = PaymentOption.MONNIFY
                        onSelectedOptionChanged(selectedOption)
                    }
                )
            }
        }
    }
}

@Composable
fun PaymentDetailsCard(
    amount: String, provider: String, mobileNumber: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background, // Set container background to white
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Amount",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.scrim
            )
            Text(
                text = "₦ $amount",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.scrim
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Provider",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.scrim
            )
            Text(
                text = provider,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.scrim
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Mobile Number", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 13.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), color = MaterialTheme.colorScheme.scrim
            )
            Text(
                text = mobileNumber, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 13.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

    }


}

@Composable
fun TransactionPinBottomSheetContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context,
    selectedOption: PaymentOption,
    onOtpChanged: (String) -> Unit
) {
    //  Toast.makeText(context,"Transaction successfully",Toast.LENGTH_SHORT).show()
    var otpText by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        Icon(
            modifier = modifier.size(30.dp),
            painter = painterResource(id = R.drawable.baseline_lock_open_24),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.enter_pin).uppercase(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary
            ),
        )
        Spacer(modifier = modifier.height(20.dp))
        TransactionPinInput {
            otpText = it
        }
        Spacer(modifier = modifier.height(20.dp))
        Text(
            text = stringResource(R.string.pinMessage),
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = modifier.height(30.dp))
    }
}

@Composable
fun TransactionPinInput(
    modifier: Modifier = Modifier,
    onDone: (String) -> Unit

) {


    var otpText by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val visualTransformation = PasswordVisualTransformation()
    BasicTextField(
        value = otpText,
        onValueChange = {
            otpText = it
            onDone(otpText)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )

    {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(5) { index ->
                val number = when {
                    index >= otpText.length -> ""
                    else -> otpText[index]
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                ) {
                    Text(text = number.toString())
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(3.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }

        }
    }
}

@Composable
fun NetworkSelector(
    onSelect: (NETWORK) -> Unit, modifier: Modifier = Modifier
) {
    var selectedNetwork by remember { mutableStateOf<NETWORK?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background, // Set container background to white
            contentColor = MaterialTheme.colorScheme.background // Use theme's onBackground color for text
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ), shape = RoundedCornerShape(15.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.select_network_provider),
                modifier = modifier
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(R.font.poppins_bold),
                    fontSize = 13.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),


                )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                repeat(NETWORK.entries.size) { index ->
                    val network = NETWORK.entries[index] // Assuming NETWORK is an enum
                    val borderColor = if (selectedNetwork == network) Color.Black else Color.White
                    val image = when (network) {
                        NETWORK.AIRTEL -> R.drawable.airtel_nigeria
                        NETWORK.MTN -> R.drawable.mtn
                        NETWORK.GLO -> R.drawable.globacom_limited
                        NETWORK.NINE_MOBILE -> R.drawable.etisalat
                    }
                    Card(
                        // Adjust the elevation as needed
                        modifier = modifier
                            .padding(8.dp)
                            .size(70.dp)
                            .border(
                                width = 3.dp, color = borderColor, shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        Image(painter = painterResource(id = image),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(15.dp))

                                .padding(5.dp)
                                .clickable {
                                    selectedNetwork = network
                                    onSelect(network)
                                })
                    }
                }
            }
        }
    }
}


fun validateInput(
    type: INPUT_TYPE,
    input: String,
    cpassword: String = "",

): Boolean {
    // Implement your validation logic based on the input type
    return when (type) {
        INPUT_TYPE.PASSWORD -> validatePassword(input)
        INPUT_TYPE.EMAIL -> validateEmail(input)
        INPUT_TYPE.PHONE -> validatePhone(input)
        INPUT_TYPE.NUMBER -> validateNumber(input)
        INPUT_TYPE.ORDERS -> validateOthers(input)
        INPUT_TYPE.CPASSWORD -> validateCP(input,cpassword)
    }
}

fun validatePassword(password: String): Boolean {
    // Add your password validation logic here
    // For example, check if it's at least 8 characters long, contains at least one letter and one number, etc.
    val passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
    return passwordPattern.matcher(password).matches()
}

fun isInternetConnected(context: Context): Boolean {
 /*
    """
    Checks if the device has a stable internet connection.

    Args:
        context: The application context.

    Returns:
        True if the device has an active internet connection, False otherwise.
    """

  */
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        // For older SDK versions, use legacy methods for compatibility
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected
    }
}

fun validateEmail(email: String): Boolean {
    // Add your email validation logic here
    // For example, check if it's in the correct format using a regular expression
    val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
    return emailPattern.matcher(email).matches()
}

fun validatePhone(phone: String): Boolean {
    // Add your phone validation logic here
    // For example, check if it's in the correct format using a regular expression
    val phonePattern = Pattern.compile("^\\+?[0-9]{11}$")
    return phonePattern.matcher(phone).matches()
}

fun validateNumber(number: String): Boolean {
    // Add your number validation logic here
    // For example, check if it's a valid number
    return try {
        number.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun validateCP(password: String, c_password: String): Boolean {

    return password == c_password
}

fun validateOthers(string: String): Boolean {
    if (string.isEmpty() || string.length < 3) {
        return false
    }
    return true
}

fun getErrorMessage(type: INPUT_TYPE): String {
    // Implement your error message logic based on the input type
    // For example, return a different error message for invalid email, password, etc.
    return when (type) {
        INPUT_TYPE.PASSWORD -> "Invalid password"
        INPUT_TYPE.EMAIL -> "Invalid email"
        INPUT_TYPE.PHONE -> "Invalid phone number"
        INPUT_TYPE.NUMBER -> "Invalid number"
        INPUT_TYPE.ORDERS -> "Invalid orders"
        INPUT_TYPE.CPASSWORD -> "Password not matched"
    }
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

@Preview()
@Composable
private fun BottomSheetPreview() {
    PAYWIZZARDTheme {
        val navController = rememberNavController()
        FundWalletButtomSheetContent(
            navController,
            onBankTransferClicked = { /*TODO*/ },
            onManualFundingClicked = {}) {
            // on monify
        }
    }
}

@Preview()
@Composable
private fun NetworkSelectorPreview() {
    PAYWIZZARDTheme {
        val context = LocalContext.current
        val navController = rememberNavController()
        NetworkSelector(onSelect = {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        })
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPurchaseDialogContentCardPreview() {
    PAYWIZZARDTheme {
        ConfirmPurchaseDialogContentCard(
            paymentType = TRANSACTION_TYPE.AIRTIME,
            amount = "200",
            provider = NETWORK.MTN.name,
            mobileNumber = "09030863146"
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionPinPreview() {
    val context = LocalContext.current

    PAYWIZZARDTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            var otpText by remember {
                mutableStateOf("")
            }
            when (otpText.length) {
                5 -> {

                }
            }
            TransactionPinInput {
                otpText = it
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowError(type: INPUT_TYPE, email: String, password: String, cpassword: String) {

    when (type) {
        INPUT_TYPE.EMAIL -> {
            if (!validateEmail(email))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    text = getErrorMessage(type),
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
        }


        INPUT_TYPE.PASSWORD -> {
            if (!validatePassword(password))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    text = getErrorMessage(type),
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
        }

        INPUT_TYPE.CPASSWORD -> {
            if (!validateCP( password, cpassword))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    text = getErrorMessage(type),
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
        }

        else -> {}
    }


}


@Preview(showBackground = true)
@Composable
private fun LoadingBottomSheetContentPreview() {
    PAYWIZZARDTheme {
        /*
         val loginState = LoginState.Error
         val activity: Activity
         AuthLoadingBottomSheetContent(loginState = loginState) {
            // loginState = LoginState.Idle
             Log.i("TAG","ondismisswed")
         }

         */

    }
}


//PaymentOptions
@Preview(showBackground = true)
@Composable
private fun PaymentOptionsCardPreview(){

    PAYWIZZARDTheme (){

        PaymentOptionsCard {

        }
    }

}


@Preview(showBackground = true)
@Composable
private fun TransactionPinSheetPreview() {

    val context = LocalContext.current
    val navController = rememberNavController()

    PAYWIZZARDTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            TransactionPinBottomSheetContent(
                navController = navController,
                context = context,
                selectedOption = PaymentOption.MONNIFY
            ) {
                Toast.makeText(
                    context,
                    "Transaction successfully with the following pin $it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}