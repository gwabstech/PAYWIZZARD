package com.paywizzard.app.screens.authPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paywizzard.app.R
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EditText
import com.paywizzard.app.components.INPUT_TYPE
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@Composable
fun PersonalInfoPage(
    firstNameValue:String,
    lastNameValue: String,
    userNameValue:String,
    phoneNumberValue:String,
    refCodeValue: String = "",
    onFirstNameChanged:(String) -> Unit,
    onLastNameChanged:(String) -> Unit,
    onUserNameChanged:(String) -> Unit,
    onPhoneNumberChanged:(String) -> Unit,
    onRefCodeChanged:(String) -> Unit,
    onRegister:()->Unit

){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Add your image as the background
        Image(
            painter = painterResource(id = R.drawable.login_background), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Column for your login UI
        Column(
            modifier = Modifier
                .padding(top = 110.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .background(color = Color.Transparent),
            verticalArrangement = Arrangement.Center,

            ) {
            // Add your login UI components here

            Text(
                text = "Personal Info",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(25.dp))


            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState(), true)
                    .background(color = Color.Transparent)
                    .padding(top = 40.dp, bottom = 20.dp),

                verticalArrangement = Arrangement.Center,
            ) {


                EditText(
                    value = firstNameValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.firstName),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onFirstNameChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                EditText(
                    value = lastNameValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.lastName),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onLastNameChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                EditText(
                    value = phoneNumberValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.phone_number),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onPhoneNumberChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                EditText(
                    value = userNameValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.userName),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChanged = {
                        onUserNameChanged(it)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                EditText(
                    value = refCodeValue,
                    type = INPUT_TYPE.ORDERS,
                    label = stringResource(id = R.string.refCode),
                    errorMessage = "",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    onValueChanged = {
                        onRefCodeChanged(it)
                    }
                )
                Spacer(modifier = Modifier.height(30.dp))
                BlueButton(
                    title = stringResource(R.string.next),
                    modifier = Modifier.padding(bottom = 50.dp)
                ) {
                    onRegister()
                }
                Spacer(modifier = Modifier.height(50.dp))
            }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PersonalInfoPreview(){
    PAYWIZZARDTheme {
        PersonalInfoPage(
            firstNameValue = "",
            lastNameValue = "",
            userNameValue = "",
            phoneNumberValue = "",
            onFirstNameChanged = {},
            onLastNameChanged = {},
            onUserNameChanged = {},
            onPhoneNumberChanged = {},
            onRefCodeChanged = {}
        ) {

        }
    }
}