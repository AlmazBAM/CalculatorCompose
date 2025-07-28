package com.bagmanovam.calculator.di

import com.bagmanovam.calculator.manager.presentation.main_screen.CalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::CalculatorViewModel)
}