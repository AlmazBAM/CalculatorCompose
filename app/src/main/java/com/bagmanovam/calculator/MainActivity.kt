package com.bagmanovam.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bagmanovam.calculator.manager.presentation.main_screen.CalculatorViewModel
import com.bagmanovam.calculator.ui.theme.CalculatorTheme
import com.bagmanovam.calculator.ui.theme.greenBackground
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    CalculatorTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        ModalNavigationDrawer(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface),
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                ) {
                    Text(
                        text = "Menu",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        label = {
                            Text(
                                text = "Calculator",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 24.sp,
                                    color = if (navBackStackEntry?.destination?.hasRoute(Calculator::class) == true) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurface
                                ),
                            )
                        },
                        selected = navBackStackEntry?.destination?.hasRoute(Calculator::class) == true,
                        onClick = {
                            navController.navigate(Calculator) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        label = {
                            Text(
                                text = "BMI calculator",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 24.sp,
                                    color = if (navBackStackEntry?.destination?.hasRoute(BMICalculator::class) == true) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurface
                                ),
                            )
                        },
                        selected = navBackStackEntry?.destination?.hasRoute(BMICalculator::class) == true,
                        onClick = {
                            navController.navigate(BMICalculator) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        ) {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(greenBackground),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Calculator") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu button"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                    )
                }
            ) { innerPadding ->
                val calculatorViewModel = koinViewModel<CalculatorViewModel>()
                val uiState by calculatorViewModel.uiState.collectAsStateWithLifecycle()

                CalculatorNavHost(
                    modifier = Modifier
                        .padding(bottom = innerPadding.calculateBottomPadding()),
                    uiState = uiState,
                    navHostController = navController,
                    onClickOperation = calculatorViewModel::onEvent
                )

            }
        }
    }
}