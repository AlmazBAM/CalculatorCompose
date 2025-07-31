package com.bagmanovam.calculator.manager.presentation.bmi.events

import com.bagmanovam.calculator.core.data.Gender

sealed interface BmiCalculatorEvent {
    data class OnGenderSelected(val gender: Gender): BmiCalculatorEvent
    data object OnWeightPlusClicked: BmiCalculatorEvent
    data object OnWeightMinusClicked: BmiCalculatorEvent
    data object OnAgePlusClicked: BmiCalculatorEvent
    data object OnAgeMinusClicked: BmiCalculatorEvent
    data class OnHeightEntered(val height: String): BmiCalculatorEvent
}