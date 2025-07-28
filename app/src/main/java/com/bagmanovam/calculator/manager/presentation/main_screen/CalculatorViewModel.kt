package com.bagmanovam.calculator.manager.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.calculator.core.data.Buttons
import com.bagmanovam.calculator.core.presentation.utils.toDisplayableString
import com.bagmanovam.calculator.manager.presentation.main_screen.events.CalculatorEvent
import com.bagmanovam.calculator.manager.presentation.main_screen.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        UiState()
    )

    fun onEvent(calculatorEvent: CalculatorEvent) {
        Log.e(TAG, "onEvent: $calculatorEvent")
        when (calculatorEvent) {
            is CalculatorEvent.OnAllClearButtonClick -> {
                _uiState.update {
                    it.copy(
                        expression = "",
                        result = "",
                        isError = false
                    )
                }
            }

            is CalculatorEvent.OnCalculatorCommandClick -> {
                val currentExpression = _uiState.value.expression
                _uiState.update {
                    it.copy(
                        expression = currentExpression + calculatorEvent.buttons.toDisplayableString()
                    )
                }
            }

            CalculatorEvent.OnEvaluateClick -> {}
            CalculatorEvent.OnDotClicked -> {
                val currentExpression = _uiState.value.expression
                if (!currentExpression.contains(Buttons.DOT.toDisplayableString())) {
                    _uiState.update {
                        it.copy(
                            expression = currentExpression + Buttons.DOT.toDisplayableString()
                        )
                    }
                }
            }
            CalculatorEvent.OnParenthesisClicked -> {}
            CalculatorEvent.OnPercentClicked -> {}
        }
    }

    companion object {
        private val TAG = CalculatorViewModel::class.java.simpleName
    }
}