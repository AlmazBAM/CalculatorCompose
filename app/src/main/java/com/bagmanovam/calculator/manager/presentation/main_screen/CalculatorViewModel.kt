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
import org.mariuszgromada.math.mxparser.Expression

class CalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        UiState()
    )

    private var expression = ""

    fun onEvent(calculatorEvent: CalculatorEvent) {
        Log.e(TAG, "onEvent: $calculatorEvent")
        when (calculatorEvent) {
            is CalculatorEvent.OnAllClearButtonClick -> {
                expression = ""
                _uiState.update {
                    it.copy(
                        expression = "",
                        result = "",
                        isError = false
                    )
                }
            }

            is CalculatorEvent.OnCalculatorCommandClick -> {
                expression += calculatorEvent.buttons.toDisplayableString()
                _uiState.update {
                    it.copy(
                        expression = expression,
                        result = evaluate() ?: ""
                    )
                }
            }

            CalculatorEvent.OnEvaluateClick -> {
                evaluate()?.let { result ->
                    expression = result
                    _uiState.update {
                        it.copy(
                            expression = result,
                            result = "",
                            isError = false,
                            isSuccess = true
                        )
                    }
                } ?: run {
                    _uiState.update {
                        it.copy(
                            expression = expression,
                            result = "",
                            isError = true,
                            isSuccess = false
                        )
                    }
                }
            }

            CalculatorEvent.OnDotClicked -> {
                expression += Buttons.DOT.toDisplayableString()
                if (!expression.contains(Buttons.DOT.toDisplayableString())) {
                    _uiState.update {
                        it.copy(
                            expression = expression
                        )
                    }
                }
            }

            CalculatorEvent.OnParenthesisClicked -> {
                expression += getCorrectParenthesis()
                _uiState.update {
                    it.copy(
                        expression = expression
                    )
                }
            }

            CalculatorEvent.OnPercentClicked -> {}
        }
    }

    private fun evaluate(): String? {
        val result = expression.replace('x', '*')
            .replace(',', '.')
            .let { Expression(it) }
            .calculate()
            .takeIf { it.isFinite() }?.toString()?.replace('.', ',')
        Log.e(TAG, "evaluate: $result", )
        return result

    }

    private fun getCorrectParenthesis(): String {
        val openParenthesis = Buttons.PARENTHESIS.toDisplayableString().first()
        val closeParenthesis = Buttons.PARENTHESIS.toDisplayableString().last()
        val piChar = Buttons.PI.toDisplayableString().first()

        val openParenthesisCount = expression.count { it == openParenthesis }
        val closeParenthesisCount = expression.count { it == closeParenthesis }

        return when {
            expression.isEmpty() -> openParenthesis.toString()
            !expression.last()
                .isDigit() && expression.last() != closeParenthesis && expression.last() != piChar -> openParenthesis.toString()

            openParenthesisCount > closeParenthesisCount -> closeParenthesis.toString()
            else -> openParenthesis.toString()
        }
    }

    companion object {
        private val TAG = CalculatorViewModel::class.java.simpleName
    }
}