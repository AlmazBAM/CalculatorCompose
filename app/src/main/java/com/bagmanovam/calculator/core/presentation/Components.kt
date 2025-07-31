package com.bagmanovam.calculator.core.presentation

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.calculator.R
import com.bagmanovam.calculator.ui.theme.CalculatorTheme

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    symbol: String,
    backgroundContainerColor: Color,
    backgroundContentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundContainerColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 24.sp,
                color = backgroundContentColor
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DataInputCard(
    modifier: Modifier = Modifier,
    value: Int,
    @StringRes label: Int,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = context.getString(label),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    color = MaterialTheme.colorScheme.secondary
                ),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.size(40.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = onMinusClicked,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "minus button"
                    )
                }
                Button(
                    modifier = Modifier.size(40.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = onPlusClicked,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "plus button"
                    )
                }
            }
        }
    }
}

@Composable
fun TextInputCard(
    modifier: Modifier = Modifier,
    value: String,
    @StringRes label: Int,
    onHeightEntered: (String) -> Unit = {},
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = context.getString(label),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                textAlign = TextAlign.Center
            )
            TextField(
                modifier = modifier
                    .wrapContentWidth()
                    .focusRequester(focusRequester)
                    .focusable()
                    .onFocusChanged { focusState ->
                        Log.e("TAG", "TextField focus state: $focusState")
                        if (focusState.isFocused) {
                            keyboardController?.show()
                        }
                    },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                ),
                onValueChange = { onHeightEntered(it) },
                value = value,
                placeholder = {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 24.sp,
                            lineHeight = 24.sp,
                        ),
                        textAlign = TextAlign.Center
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }

        }
    }
}

@Preview
@Composable
private fun TextInputCardPreview() {
    CalculatorTheme {
        TextInputCard(
            modifier = Modifier,
            value = "65",
            label = R.string.height,
        )
    }
}

@Preview
@Composable
private fun DataInputCardPreview() {
    CalculatorTheme {
        DataInputCard(
            modifier = Modifier,
            value = 65,
            label = R.string.height,
            onPlusClicked = {},
            onMinusClicked = {}
        )
    }
}