package com.bagmanovam.calculator.manager.presentation.main_screen.state

import androidx.compose.runtime.Immutable

@Immutable
data class UiState(
    val expression: String = "",
    val result: String = "",
    val isError: Boolean = false,
    val isSuccess: Boolean = false
)
