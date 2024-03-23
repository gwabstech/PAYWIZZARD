package com.paywizzard.app.screens.authPages

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.R
import com.paywizzard.app.activities.AuthActivity
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EditText
import com.paywizzard.app.components.INPUT_TYPE
import com.paywizzard.app.components.LoadingBottomSheetContent
import com.paywizzard.app.components.validateOthers
import com.paywizzard.app.components.validatePhone
import com.paywizzard.app.data.models.LoginState
import com.paywizzard.app.data.models.RegisterState
import com.paywizzard.app.data.viewModels.RegisterViewModel
import com.paywizzard.app.nav.AuthDestination
import com.paywizzard.app.network.RetrofitClient
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoPage(
   // context: AuthActivity ,
    registerViewModel: RegisterViewModel,
    navController: NavHostController

) {


    val registerState by registerViewModel.registerState.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var isLoadingSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }


    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }

    var userName by remember {
        mutableStateOf("")
    }
    var referralCode by remember {
        mutableStateOf("")
    }


    var showFirstNameError by remember {
        mutableStateOf(false)
    }

    var showLastNameError by remember {
        mutableStateOf(false)
    }
    var showPhoneNumberError by remember {
        mutableStateOf(false)
    }

    var showUserNameError by remember {
        mutableStateOf(false)
    }






    Column(
        modifier = Modifier
            .padding(13.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), true)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.End).size(65.dp)
                .padding(end = 13.dp)
        )

        // Add your login UI components here

        Text(
            text = "Personal Info",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(25.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
        ) {


            EditText(
                value = firstName,
                type = INPUT_TYPE.ORDERS,
                label = stringResource(id = R.string.firstName),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    firstName = it
                    registerViewModel.setFirstName(firstName)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditText(
                value = lastName,
                type = INPUT_TYPE.ORDERS,
                label = stringResource(id = R.string.lastName),


                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    lastName = it
                    registerViewModel.setLastName(lastName)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditText(
                value = phoneNumber,
                type = INPUT_TYPE.PHONE,
                label = stringResource(id = R.string.phone_number),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    phoneNumber = it
                    registerViewModel.setPhoneNumber(phoneNumber)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))


            EditText(
                value = userName,
                type = INPUT_TYPE.ORDERS,
                label = stringResource(id = R.string.userName),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = {
                    userName = it
                    registerViewModel.setUsername(userName)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Optional",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 5.dp),
                style = TextStyle(
                    fontStyle = FontStyle(R.font.poppins_medium),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 2.sp,
                    color = MaterialTheme.colorScheme.primary,
                    lineBreak = LineBreak.Simple,
                    textAlign = TextAlign.Start,
                )

            )
            EditText(
                value = referralCode,
                type = INPUT_TYPE.ORDERS,
                label = stringResource(id = R.string.refCode),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                onValueChanged = {
                    referralCode = it
                    registerViewModel.setRefCode(referralCode)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            BlueButton(
                title = "Sign up",
            ) {

                if (validatePInfo(firstName, lastName, phoneNumber, userName)) {
                    isLoadingSheetOpen = true
                    val refcode = referralCode.takeUnless { it.isNullOrEmpty() } ?: "admin"
                    registerViewModel.setRefCode(refcode)
                    registerViewModel.register()
                    // register user and show progress
                } else {
                    when {
                        !validateOthers(firstName) -> showFirstNameError = true
                        !validateOthers(lastName) -> showLastNameError = true
                        !validatePhone(phoneNumber) -> showPhoneNumberError = true
                        !validateOthers(userName) -> showUserNameError = true
                    }
                }


            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

            ) {
                Text(
                    text = "By signing up you accept the terms and conditions",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontStyle = FontStyle(R.font.poppins_medium),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        letterSpacing = 2.sp,
                        lineBreak = LineBreak.Simple,
                        textAlign = TextAlign.Start,
                    )

                )

                Text(
                    text = " Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable {  navController.navigate(AuthDestination.RegisterScreen.route) },
                    style = TextStyle(
                        fontStyle = FontStyle(R.font.poppins_medium),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        lineBreak = LineBreak.Simple,
                        textAlign = TextAlign.Start,
                    )

                )

            }

            if (isLoadingSheetOpen){
                HandleRegister(registerState = registerState, sheetState =sheetState , onDismissed = {isLoadingSheetOpen = false}){

                    navController.navigate(AuthDestination.LoginScreen.route)
                }
            }


        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleRegister(
    registerState:RegisterState,
    sheetState: SheetState,
    onDismissed:()-> Unit,
    onRegister: (String) -> Unit

) {
    when (registerState) {
        is RegisterState.Loading ->{
            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {

                LoadingBottomSheetContent(message = "Creating Account...", isError = false) {
                    onDismissed()
                }
            }
        }
        is RegisterState.Success -> {
            // Display your success message using Toast
            Log.i("TAG",registerState.massage)
            onRegister(registerState.massage)
        }

        is RegisterState.Error -> {
            // Display an error message using Toast
            Log.i("TAG",registerState.message)

            ModalBottomSheet(
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() },
                onDismissRequest = { onDismissed() }
            ) {
                LoadingBottomSheetContent(message = registerState.message, isError = true) {
                    onDismissed()
                }
            }

        }
    }
}

private fun validatePInfo(fName:String,lName:String,phone:String,userName:String): Boolean {
   return true// (validateOthers(fName) && validateOthers(lName) && validatePhone(phone) && validateOthers(userName))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PersonalInfoPreview() {
    PAYWIZZARDTheme {
        val registerViewModel = RegisterViewModel(RetrofitClient.apiService())
         val context = LocalContext.current.applicationContext
        val navController = rememberNavController()
        registerViewModel.setFirstName("")
        registerViewModel.setLastName("")
        registerViewModel.setUsername("")
        registerViewModel.setPhoneNumber("")
        registerViewModel.setRefCode()
        PersonalInfoPage(registerViewModel = registerViewModel, navController = navController)
    }
}