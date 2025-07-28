package com.bagmanovam.calculator.manager.presentation.main_screen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.bagmanovam.calculator.core.data.Buttons.*
import com.bagmanovam.calculator.core.presentation.ButtonComponent
import com.bagmanovam.calculator.core.presentation.utils.toDisplayableString
import com.bagmanovam.calculator.manager.presentation.main_screen.events.CalculatorEvent
import com.bagmanovam.calculator.ui.theme.CalculatorTheme

@Composable
fun KeyBoard(
    modifier: Modifier = Modifier,
    onClickOperation: (CalculatorEvent) -> Unit,
) {

    val list = listOf(
        listOf(CLEAR, PARENTHESIS, PERCENT, DIVIDE),
        listOf(DIGIT_7, DIGIT_8, DIGIT_9, MULTIPLY),
        listOf(DIGIT_4, DIGIT_5, DIGIT_6, PLUS),
        listOf(DIGIT_1, DIGIT_2, DIGIT_3, MINUS),
        listOf(DIGIT_0, DOT, EVALUATE),
    )
    val digitRegex = Regex("""\d""")
    val spacing = 8.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
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
                    modifier = modifier
                        .weight(1f)
                        .clickable {
                            onClickOperation(CalculatorEvent.OnCalculatorCommandClick(SQRT))
                        },
                    text = SQRT.toDisplayableString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier
                        .weight(1f)
                        .clickable {
                            onClickOperation(CalculatorEvent.OnCalculatorCommandClick(PI))
                        },
                    text = PI.toDisplayableString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier
                        .weight(1f)
                        .clickable {
                            onClickOperation(CalculatorEvent.OnCalculatorCommandClick(POWER))
                        },
                    text = POWER.toDisplayableString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier
                        .weight(1f)
                        .clickable {
                            onClickOperation(CalculatorEvent.OnCalculatorCommandClick(FACTORIAL))
                        },
                    text = FACTORIAL.toDisplayableString(),
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
                        digitRegex.matches(symbol.toDisplayableString())
                        val weightAndAspectRatio = when {
                            symbol == DIGIT_0 -> 2f
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
                            symbol = symbol.toDisplayableString(),
                            backgroundContainerColor = backgroundContainerColor,
                            backgroundContentColor = backgroundContentColor,
                            onClick = {
                                when (symbol) {
                                    CLEAR -> onClickOperation(CalculatorEvent.OnAllClearButtonClick)
                                    EVALUATE -> onClickOperation(CalculatorEvent.OnEvaluateClick)
                                    DOT -> onClickOperation(CalculatorEvent.OnDotClicked)
                                    else -> onClickOperation(
                                        CalculatorEvent.OnCalculatorCommandClick(
                                            symbol
                                        )
                                    )
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
            onClickOperation = {}
        )
    }
}
