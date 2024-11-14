package com.sijan.liquidbottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.tooling.preview.Preview
import com.sijan.liquidbottomnav.ui.theme.LiquidBottomNavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiquidBottomNavTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val isMenuExtended = remember { mutableStateOf(false) }
                    val fabAnimationProgress by animateFloatAsState(
                        targetValue = if (isMenuExtended.value) 1f else 0f,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = LinearEasing
                        ),
                        label = ""
                    )
                    val renderEffect = getRenderEffect().asComposeRenderEffect()
                    val clickAnimationProgress by animateFloatAsState(
                        targetValue = if (isMenuExtended.value) 1f else 0f,
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = LinearEasing
                        ), label = ""
                    )
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        renderEffect = renderEffect,
                        fabAnimationProgress = fabAnimationProgress,
                        clickAnimationProgress = clickAnimationProgress,
                        toggleAnimation = {
                            isMenuExtended.value = isMenuExtended.value.not()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiquidBottomNavTheme {
        Greeting("Android")
    }
}