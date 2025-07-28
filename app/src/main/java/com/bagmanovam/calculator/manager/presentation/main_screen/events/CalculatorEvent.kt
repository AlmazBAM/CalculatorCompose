package com.bagmanovam.calculator.manager.presentation.main_screen.events

import com.bagmanovam.calculator.core.data.Buttons

sealed interface CalculatorEvent {
    data object OnAllClearButtonClick : CalculatorEvent
    data object OnEvaluateClick : CalculatorEvent
    data object OnDotClicked : CalculatorEvent
    data object OnParenthesisClicked : CalculatorEvent
    data object OnPercentClicked : CalculatorEvent
    data class OnCalculatorCommandClick(val buttons: Buttons) : CalculatorEvent
}
