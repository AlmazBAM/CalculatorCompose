package com.bagmanovam.calculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bagmanovam.calculator.manager.presentation.bmi.BMICalculator
import com.bagmanovam.calculator.manager.presentation.main_screen.Calculator
import com.bagmanovam.calculator.manager.presentation.main_screen.events.CalculatorEvent
import com.bagmanovam.calculator.manager.presentation.main_screen.state.UiState

@Composable
fun CalculatorNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
    uiState: UiState,
    onClickOperation: (CalculatorEvent) -> Unit
) {

    NavHost(
        navController = navHostController,
        startDestination = Calculator
    ) {
        composable<Calculator> {
            Calculator(
                modifier = modifier,
                uiState = uiState,
                onClickOperation = onClickOperation
            )
        }

        composable<BMICalculator> {
            BMICalculator(

            )
        }
    }
}