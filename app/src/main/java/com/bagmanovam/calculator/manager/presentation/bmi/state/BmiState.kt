package com.bagmanovam.calculator.manager.presentation.bmi.state

import com.bagmanovam.calculator.core.data.Gender
import java.text.NumberFormat
import java.util.Locale

data class BmiState(
    val gender: Gender = Gender.NOT_SPECIFIED,
    val age: Int = 30,
    val weight: Int = 65,
    val height: Int = 170
)

private fun BmiState.bmi(): Float = (age + weight).toFloat()
fun BmiState.toDisplayableValue(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 1
        maximumFractionDigits = 1
    }
    return formatter.format(bmi())
}

