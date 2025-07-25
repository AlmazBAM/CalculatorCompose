package com.bagmanovam.calculator.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bagmanovam.calculator.R


val manrope = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_bold, FontWeight.Bold),
    Font(R.font.manrope_medium, FontWeight.Medium),
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyMedium = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Normal,
    ),
    bodyLarge = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.SemiBold,
    ),
    labelMedium = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Normal,
    ),
    labelLarge = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = manrope,
        fontWeight = FontWeight.SemiBold
    )
)