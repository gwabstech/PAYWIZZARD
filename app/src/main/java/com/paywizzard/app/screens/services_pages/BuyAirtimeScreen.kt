package com.paywizzard.app.screens.services_pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.paywizzard.app.R
import com.paywizzard.app.components.BlueButton
import com.paywizzard.app.components.EditText
import com.paywizzard.app.components.GeneraleTopAppBar
import com.paywizzard.app.components.INPUT_TYPE
import com.paywizzard.app.components.NETWORK
import com.paywizzard.app.components.NetworkSelector
import com.paywizzard.app.components.TransactionPinBottomSheetContent
import com.paywizzard.app.nav.HomeScreenNavDestinations
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyAirtimeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues

) {
    val context = LocalContext.current

    var selectedNetwork by remember {
        mutableStateOf<NETWORK?>(null)

    }
    var amount by remember {
        mutableStateOf("")

    }
    var phone by remember {
        mutableStateOf("")

    }
    val transactionSheetState = rememberModalBottomSheetState()
    val confirmSheetState = rememberModalBottomSheetState()

    var showTransactionPinSheet by rememberSaveable {
        mutableStateOf(false)
    }
    var showConfirmationSheet by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        GeneraleTopAppBar(onBackArrowClick = { navController.popBackStack() }) {
            navController.navigate(HomeScreenNavDestinations.TransactionsScreen.route)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()

                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier = Modifier.height(20.dp))
            NetworkSelector(onSelect = {
                selectedNetwork = it
            })
            Spacer(modifier = Modifier.height(10.dp))


            NumberAndAmountSection(
                amount = amount,
                phoneNumber = phone,
                onAmountChange = {
                    amount = it.toString()
                }, onPhoneNumberChanged = {
                    phone = it
                },
                onSuggestedAmountChanged = {
                    amount = it.toString()
                }
            )
        }


        if (showTransactionPinSheet) {
            if (selectedNetwork != null) {
                ModalBottomSheet(
                    sheetState = transactionSheetState,
                    dragHandle = { BottomSheetDefaults.DragHandle() },
                    onDismissRequest = { showTransactionPinSheet = false }
                ) {

                    TransactionPinBottomSheetContent(context = context, onOtpChanged = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.successMessage),
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                }
            } else {
                Toast.makeText(context, "Please Select a network...!", Toast.LENGTH_SHORT).show()
            }

        }


    }

}


@Composable
fun NumberAndAmountSection(
    amount: String,
    phoneNumber: String,
    onAmountChange: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onSuggestedAmountChanged: (String)-> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "Choose Beneficiary",
                style = MaterialTheme.typography.titleSmall
            )
        }

        EditText(
            value = phoneNumber,
            type = INPUT_TYPE.PHONE,
            label = "Phone number",

            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            onValueChanged = {
                onPhoneNumberChanged(it)
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        EditText(
            value = amount.toString(),
            type = INPUT_TYPE.NUMBER,
            label = "Amount",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            onValueChanged = {
                onAmountChange(it)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        SuggestedAmounts {
            onSuggestedAmountChanged(it)
        }

        Spacer(modifier = Modifier.height(150.dp))
        BlueButton(title = "Proceed") {

            //  onshow(true)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun SuggestedAmounts(
    modifier: Modifier = Modifier,
    onAmountSelected: (String) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val amounts = listOf(200, 500, 1000)

        amounts.forEach { amount ->
            Button(
                onClick = { onAmountSelected(amount.toString()) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "$amount",
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BuyAirtimeScreenPreview() {
    PAYWIZZARDTheme {
        val navController = rememberNavController()

        BuyAirtimeScreen(navController = navController, paddingValues = PaddingValues(0.dp))

    }
}


@Preview(showBackground = true)
@Composable
private fun AmountSectionPreview() {
    PAYWIZZARDTheme {
        var number by remember {
            mutableStateOf(String)
        }
        var amount by remember {
            mutableStateOf(String)
        }

    }
}




