package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.backgroundColorForCircle


@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 68.sp,
    radius: Dp = 100.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 20.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    // state where animation is played or not
    // commit check
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) { percentage } else if (percentage >= 1f) { 0f } else { 0f },
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
            val size: Dp = 180.dp
            drawCircle(
                color = backgroundColorForCircle.copy(alpha = 0.3f),
                radius = size.toPx() / 2,
                style = Stroke(width = 30f, cap = StrokeCap.Round)
            )
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


