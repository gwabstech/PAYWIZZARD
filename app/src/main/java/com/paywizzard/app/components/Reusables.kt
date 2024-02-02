package com.paywizzard.app.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paywizzard.app.R
import com.paywizzard.app.screens.authPages.GetStartedPage
import com.paywizzard.app.ui.theme.PAYWIZZARDTheme

enum class INPUT_TYPE{
    PASSWORD,
    EMAIL,
    ORDERS
}

@Composable
fun BottomAppBar(){

}

@Composable
fun GeneraleTopAppBar(
    title:String = "Airtime",
    onBackArrowClick:() -> Unit,
    onHistoryClick: () -> Unit
){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
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
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xffD9D9D9),
            unfocusedBorderColor = Color(0x56020040) //
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

@Preview( showBackground = true)
@Composable
private fun GetStartedPreview(){
    PAYWIZZARDTheme {
        GeneraleTopAppBar(title = "Airtime", onBackArrowClick = { /*TODO*/ }) {

        }
    }
}