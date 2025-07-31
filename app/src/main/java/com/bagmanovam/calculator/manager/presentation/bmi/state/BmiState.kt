package com.bagmanovam.calculator.manager.presentation.bmi.state

import com.bagmanovam.calculator.core.data.Gender
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

data class BmiState(
    val gender: Gender = Gender.NOT_SPECIFIED,
    val age: Int = 30,
    val weight: Int = 65,
    val height: String = "170"
)

private fun BmiState.bmi(): Float {
    val heightDouble = height.toDouble() / 100

    val bmi = (weight / (heightDouble.pow(2))).toFloat()
    return bmi
}
fun BmiState.toDisplayableValue(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 1
        maximumFractionDigits = 1
    }
    return formatter.format(bmi())
}

