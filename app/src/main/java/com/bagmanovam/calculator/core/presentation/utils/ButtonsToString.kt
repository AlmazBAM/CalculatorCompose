package com.bagmanovam.calculator.core.presentation.utils

import com.bagmanovam.calculator.core.data.Buttons

fun Buttons.toDisplayableString(): String {
    return when (this) {
        Buttons.DIGIT_0 -> "0"
        Buttons.DIGIT_1 -> "1"
        Buttons.DIGIT_2 -> "2"
        Buttons.DIGIT_3 -> "3"
        Buttons.DIGIT_4 -> "4"
        Buttons.DIGIT_5 -> "5"
        Buttons.DIGIT_6 -> "6"
        Buttons.DIGIT_7 -> "7"
        Buttons.DIGIT_8 -> "8"
        Buttons.DIGIT_9 -> "9"
        Buttons.DOT -> ","
        Buttons.CLEAR -> "AC"
        Buttons.PLUS -> "+"
        Buttons.MINUS -> "−"
        Buttons.MULTIPLY -> "×"
        Buttons.DIVIDE -> "÷"
        Buttons.PARENTHESIS -> "()"
        Buttons.PERCENT -> "%"
        Buttons.SQRT -> "√"
        Buttons.PI -> "π"
        Buttons.POWER -> "^"
        Buttons.FACTORIAL -> "!"
        Buttons.EVALUATE -> "="
    }
}