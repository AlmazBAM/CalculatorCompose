package com.bagmanovam.calculator.manager.presentation.bmi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.calculator.R
import com.bagmanovam.calculator.core.data.Gender
import com.bagmanovam.calculator.manager.presentation.bmi.events.BmiCalculatorEvent
import com.bagmanovam.calculator.manager.presentation.bmi.state.BmiState
import com.bagmanovam.calculator.ui.theme.CalculatorTheme

@Composable
fun BMICalculator(
    modifier: Modifier = Modifier,
    uiState: BmiState,
    onGenderSelected: (BmiCalculatorEvent) -> Unit,
    onContinueClicked: () -> Unit,
    paddingValues: PaddingValues,
) {

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier,
            text = stringResource(R.string.choose_gender),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
        )
        Column(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        if (uiState.gender == Gender.MALE)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(24.dp)
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(onPress = {
                            onGenderSelected(
                                BmiCalculatorEvent.OnGenderSelected(
                                    Gender.MALE
                                )
                            )
                        })
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(R.string.male),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 32.sp,
                        color =
                            if (uiState.gender == Gender.MALE)
                                MaterialTheme.colorScheme.onPrimaryContainer
                            else
                                MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
                Image(
                    modifier = modifier
                        .size(150.dp),
                    painter = painterResource(R.drawable.male),
                    contentDescription = "male image",
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        if (uiState.gender == Gender.FEMALE)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(24.dp)
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(onPress = {
                            onGenderSelected(
                                BmiCalculatorEvent.OnGenderSelected(
                                    Gender.FEMALE
                                )
                            )
                        })
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(R.string.female),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 32.sp,
                        color =
                            if (uiState.gender == Gender.MALE)
                                MaterialTheme.colorScheme.onPrimaryContainer
                            else
                                MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
                Image(
                    modifier = modifier
                        .size(150.dp),
                    painter = painterResource(R.drawable.female),
                    contentDescription = "male image",
                )
            }

            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { onContinueClicked() }
            ) {
                Text(
                    text = stringResource(R.string.continue_button),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun BMICalculatorPreview() {
    CalculatorTheme {
        Scaffold { paddings ->
            BMICalculator(
                modifier = Modifier
                    .padding(bottom = paddings.calculateBottomPadding()),
                uiState = uiState,
                onGenderSelected = {},
                onContinueClicked = {},
                paddingValues = PaddingValues(),
            )
        }
    }
}

internal val uiState = BmiState(gender = Gender.MALE)