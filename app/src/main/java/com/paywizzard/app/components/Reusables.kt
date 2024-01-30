package com.paywizzard.app.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class INPUT_TYPE{
    PASSWORD,
    EMAIL,
    ORDERS
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
                style = MaterialTheme.typography.displayMedium
            )
        },
        onValueChange = {
            onValueChanged(it)

            isValid = validateInput(type, it)
        },
        shape = RoundedCornerShape(10.dp),
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
