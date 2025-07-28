package com.bagmanovam.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagmanovam.calculator.manager.presentation.main_screen.Calculator
import com.bagmanovam.calculator.manager.presentation.main_screen.CalculatorViewModel
import com.bagmanovam.calculator.ui.theme.CalculatorTheme
import com.bagmanovam.calculator.ui.theme.greenBackground
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    CalculatorTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(greenBackground)
        ) { innerPadding ->
            val calculatorViewModel = koinViewModel<CalculatorViewModel>()
            val uiState by calculatorViewModel.uiState.collectAsStateWithLifecycle()
            Calculator(
                modifier = Modifier
                    .padding(bottom = innerPadding.calculateBottomPadding()),
                uiState = uiState,
                onClickOperation = calculatorViewModel::onEvent
            )
        }
    }
}