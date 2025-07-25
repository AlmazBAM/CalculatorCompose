package com.bagmanovam.calculator.manager.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.calculator.core.presentation.ButtonComponent
import com.bagmanovam.calculator.ui.theme.CalculatorTheme

@Composable
fun KeyBoard(
    modifier: Modifier = Modifier,
    onClickDigit: (String) -> Unit,
    onClickOperation: (String) -> Unit,
) {

    val list = listOf(
        listOf("AC", "()", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "+"),
        listOf("1", "2", "3", "-"),
        listOf("0", ",", "="),
    )
    val digitRegex = Regex("""\d|\.|=""")
    val spacing = 8.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {

//        val totalWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Text(
                    modifier = modifier.weight(1f),
                    text = "&",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier.weight(1f),
                    text = "pi",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier.weight(1f),
                    text = "^",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier.weight(1f),
                    text = "!",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
            }
            list.forEachIndexed { rowIndex, strings ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    strings.forEachIndexed { colIndex, symbol ->
                        val isDigit = digitRegex.matches(symbol)
                        val weightAndAspectRatio = when {
                            symbol == "0" -> 2f
                            else -> 1f
                        }
                        val backgroundContainerColor = when {
                            rowIndex == 0 && colIndex == 0 -> MaterialTheme.colorScheme.primary
                            rowIndex == 0 || colIndex == strings.lastIndex -> MaterialTheme.colorScheme.secondary
                            else -> MaterialTheme.colorScheme.tertiary
                        }

                        val backgroundContentColor = when {
                            rowIndex == 0 && colIndex == 0 -> MaterialTheme.colorScheme.onPrimary
                            rowIndex == 0 || colIndex == strings.lastIndex -> MaterialTheme.colorScheme.onSecondary
                            else -> MaterialTheme.colorScheme.onTertiary
                        }
                        ButtonComponent(
                            modifier = modifier
                                .weight(weightAndAspectRatio)
                                .aspectRatio(weightAndAspectRatio)
                                .clip(CircleShape),
                            symbol = symbol,
                            backgroundContainerColor = backgroundContainerColor,
                            backgroundContentColor = backgroundContentColor,
                            onClick = {
                                if (isDigit) {
                                    onClickDigit(symbol)
                                } else {
                                    onClickOperation(symbol)
                                }
                            },
                        )

                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun KeyBoardPreview() {
    CalculatorTheme {
        KeyBoard(
            modifier = Modifier,
            onClickDigit = {},
            onClickOperation = {}
        )
    }
}
