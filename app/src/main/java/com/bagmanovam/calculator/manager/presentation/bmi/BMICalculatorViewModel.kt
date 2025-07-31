package com.bagmanovam.calculator.manager.presentation.bmi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.calculator.manager.presentation.bmi.events.BmiCalculatorEvent
import com.bagmanovam.calculator.manager.presentation.bmi.state.BmiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class BMICalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BmiState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        BmiState()
    )

    private val heightRegex = Regex("^[1-2][0-9][0-9]$")

    fun onEvent(bmiCalculatorEvent: BmiCalculatorEvent) {
        when (bmiCalculatorEvent) {
            BmiCalculatorEvent.OnAgeMinusClicked -> {
                var currentAge = _uiState.value.age
                _uiState.update {
                    it.copy(
                        age = --currentAge
                    )
                }
            }

            BmiCalculatorEvent.OnAgePlusClicked -> {
                var currentAge = _uiState.value.age
                _uiState.value.age
                _uiState.update {
                    it.copy(
                        age = ++currentAge
                    )
                }
            }

            is BmiCalculatorEvent.OnGenderSelected -> {
                _uiState.update {
                    it.copy(
                        gender = bmiCalculatorEvent.gender
                    )
                }
            }

            is BmiCalculatorEvent.OnHeightEntered -> {
                _uiState.update {
                    it.copy(
                        height = bmiCalculatorEvent.height,
                        isHeightCorrect = heightRegex.matches(bmiCalculatorEvent.height)
                    )
                }
            }

            BmiCalculatorEvent.OnWeightMinusClicked -> {
                var currentWeight = _uiState.value.weight
                _uiState.update {
                    it.copy(
                        weight = --currentWeight
                    )
                }
            }

            BmiCalculatorEvent.OnWeightPlusClicked -> {
                var currentWeight = _uiState.value.weight
                _uiState.update {
                    it.copy(
                        weight = ++currentWeight
                    )
                }
            }
        }
    }

}