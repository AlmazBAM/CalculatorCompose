package com.bagmanovam.calculator.manager.presentation.bmi.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.calculator.R
import com.bagmanovam.calculator.core.presentation.DataInputCard
import com.bagmanovam.calculator.manager.presentation.bmi.events.BmiCalculatorEvent
import com.bagmanovam.calculator.manager.presentation.bmi.state.BmiState
import com.bagmanovam.calculator.ui.theme.CalculatorTheme

@Composable
fun BMIUserInputScreen(
    modifier: Modifier,
    bmiState: BmiState,
    onBackClicked: () -> Unit,
    onUserDataInput: (BmiCalculatorEvent) -> Unit,
    paddingValues: PaddingValues
) {
    var isShowDialog by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.fillMaxSize().padding(paddingValues),
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    modifier = modifier
                        .clickable { onBackClicked() },
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Menu button"
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = stringResource(R.string.bmi_calculator),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.secondary
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = stringResource(R.string.modify_values),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                textAlign = TextAlign.Center
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DataInputCard(
                    modifier = modifier
                        .weight(1f)
                        .aspectRatio(0.9f),
                    value = bmiState.weight,
                    label = R.string.weight,
                    onPlusClicked = { onUserDataInput(BmiCalculatorEvent.OnWeightPlusClicked) },
                    onMinusClicked = { onUserDataInput(BmiCalculatorEvent.OnWeightMinusClicked) }
                )
                DataInputCard(
                    modifier = modifier
                        .weight(1f)
                        .aspectRatio(0.9f),
                    value = bmiState.age,
                    label = R.string.age,
                    onPlusClicked = { onUserDataInput(BmiCalculatorEvent.OnAgePlusClicked) },
                    onMinusClicked = { onUserDataInput(BmiCalculatorEvent.OnAgeMinusClicked) }
                )
            }
            DataInputCard(
                modifier = modifier,
                isTextField = true,
                value = bmiState.height,
                label = R.string.height,
                onHeightEntered = { onUserDataInput(BmiCalculatorEvent.OnHeightEntered(it)) }
            ) { }
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = {
                    isShowDialog = true
                }
            ) {
                Text(
                    text = stringResource(R.string.calculate_button),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }

        }

        if (isShowDialog) {
            ResultScreen(
                bmiState = bmiState,
                onClickClose = {
                    isShowDialog = false
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BMIUserInputScreenPreview() {
    CalculatorTheme {
        Scaffold { inner ->
            BMIUserInputScreen(
                modifier = Modifier.padding(bottom = inner.calculateBottomPadding()),
                bmiState = BmiState(),
                onBackClicked = {},
                onUserDataInput = {},
                paddingValues = PaddingValues()
            )
        }
    }
}