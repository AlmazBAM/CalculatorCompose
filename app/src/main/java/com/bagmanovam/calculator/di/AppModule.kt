package com.bagmanovam.calculator.di

import com.bagmanovam.calculator.manager.presentation.bmi.BMICalculatorViewModel
import com.bagmanovam.calculator.manager.presentation.main_screen.CalculatorViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

val appModule = module {
    viewModelOf(::CalculatorViewModel)
    viewModelOf(::BMICalculatorViewModel)
}