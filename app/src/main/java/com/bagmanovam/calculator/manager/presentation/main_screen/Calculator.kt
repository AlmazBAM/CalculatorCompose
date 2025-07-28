package com.bagmanovam.calculator.manager.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.calculator.manager.presentation.main_screen.events.CalculatorEvent
import com.bagmanovam.calculator.manager.presentation.main_screen.screens.KeyBoard
import com.bagmanovam.calculator.manager.presentation.main_screen.state.UiState
import com.bagmanovam.calculator.ui.theme.CalculatorTheme


@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onClickOperation: (CalculatorEvent) -> Unit,
) {

    Column(
        modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .padding(bottom = 24.dp, end = 24.dp, start = 24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when {
                uiState.isError -> {
                    Text(
                        text = uiState.expression,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onError,
                            fontSize = 32.sp
                        )
                    )
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 24.sp
                        )
                    )
                }
                uiState.isSuccess -> {

                }
            }
            Text(
                text = uiState.expression,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 32.sp
                )
            )
            Text(
                text = uiState.result,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 24.sp
                )
            )
        }
        KeyBoard(
            onClickOperation = onClickOperation
        )
    }
}

@PreviewLightDark
@Composable
private fun CalculatorPreview() {
    Scaffold { inner ->
        CalculatorTheme {
            Calculator(
                modifier = Modifier.padding(inner),
                uiState = uiState,
                onClickOperation = {}
            )
        }
    }
}

internal val uiState = UiState(
    expression = "45*6",
    result = "360"
)