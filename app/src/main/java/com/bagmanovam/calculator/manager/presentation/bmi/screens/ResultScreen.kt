package com.bagmanovam.calculator.manager.presentation.bmi.screens

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bagmanovam.calculator.R
import com.bagmanovam.calculator.manager.presentation.bmi.state.BmiState
import com.bagmanovam.calculator.manager.presentation.bmi.state.toDisplayableValue
import com.bagmanovam.calculator.ui.theme.CalculatorTheme

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    bmiState: BmiState,
    onClickClose: () -> Unit
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onClickClose,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable {
                    onClickClose()
                }
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Box(
                    modifier = modifier
                        .matchParentSize()
                        .graphicsLayer {
                            renderEffect = RenderEffect.createBlurEffect(
                                20f, 20f, Shader.TileMode.CLAMP
                            ).asComposeRenderEffect()
                        }
                )
            } else {
                Box(
                    modifier = modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                )
            }
            Card(
                modifier = modifier
                    .padding(32.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
                ),
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Column(
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = context.getString(R.string.your_bmi),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 24.sp,
                            lineHeight = 24.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = bmiState.toDisplayableValue(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 32.sp,
                            lineHeight = 32.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ResultScreenPreview() {
    CalculatorTheme {
        ResultScreen(
            modifier = Modifier,
            bmiState = BmiState(),
            onClickClose = {}
        )
    }
}