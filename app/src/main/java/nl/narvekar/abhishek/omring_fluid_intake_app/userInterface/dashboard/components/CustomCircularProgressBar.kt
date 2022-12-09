package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 68.sp,
    radius: Dp = 100.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 20.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    // state where animation is played or not
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) { percentage } else { 0f },
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = "${(curPercentage.value * number).toInt().toString()}%", color = Color.Black, fontSize = fontSize)
    }
}


@Composable
fun CircularProgressBar() {
    var progress by remember { mutableStateOf(0.1f) }

}