package com.bagmanovam.calculator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bagmanovam.calculator.manager.presentation.bmi.BMICalculator
import com.bagmanovam.calculator.manager.presentation.bmi.events.BmiCalculatorEvent
import com.bagmanovam.calculator.manager.presentation.bmi.screens.BMIUserInputScreen
import com.bagmanovam.calculator.manager.presentation.bmi.state.BmiState
import com.bagmanovam.calculator.manager.presentation.main_screen.Calculator
import com.bagmanovam.calculator.manager.presentation.main_screen.events.CalculatorEvent
import com.bagmanovam.calculator.manager.presentation.main_screen.state.UiState

@Composable
fun CalculatorNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
    uiState: UiState,
    bmiState: BmiState,
    onClickOperation: (CalculatorEvent) -> Unit,
    onGenderSelected: (BmiCalculatorEvent) -> Unit,
    onUserDataInput: (BmiCalculatorEvent) -> Unit,
    paddingValues: PaddingValues,
) {

    NavHost(
        navController = navHostController,
        startDestination = Calculator
    ) {
        composable<Calculator> {
            Calculator(
                modifier = modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                uiState = uiState,
                onClickOperation = onClickOperation
            )
        }

        composable<BMICalculator> {
            BMICalculator(
                modifier = modifier,
                paddingValues = paddingValues,
                uiState = bmiState,
                onGenderSelected = onGenderSelected,
                onContinueClicked = {
                    navHostController.navigate(BMIUserInputScreen) {
                        popUpTo(
                            navHostController.currentDestination?.id
                                ?: navHostController.graph.startDestinationId
                        ) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable<BMIUserInputScreen> {
            BMIUserInputScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                bmiState = bmiState,
                onUserDataInput = onUserDataInput,
                onBackClicked = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}