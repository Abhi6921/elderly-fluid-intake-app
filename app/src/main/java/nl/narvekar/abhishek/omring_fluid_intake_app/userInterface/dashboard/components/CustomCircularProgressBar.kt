package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.backgroundColorForCircle
import kotlin.math.roundToInt


@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 68.sp,
    radius: Dp = 100.dp,
    shadowcolor: Color = Color.LightGray,
    strokeWidth: Dp = 20.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue =  if (animationPlayed) { percentage } else { 0f },
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = Unit) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        val percentProgress = ((curPercentage.value / number) * 100).roundToInt()
        Canvas(modifier = Modifier.size(radius * 2f)) {
            val size: Dp = 180.dp
            drawCircle(
                color = shadowcolor,
                radius = size.toPx() / 2,
                style = Stroke(width = 30f, cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.Blue,
                -90f,
                (360 * curPercentage.value) / number,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(text = "${percentProgress}%", color = Color.Black, fontSize = fontSize)
    }
}

@Composable
fun FluidIntakeCircularProgressBar(
    size: Dp = 300.dp,
    forgroundIndicatorColor: Color = Color(0xFF35898f),
    shadowColor: Color = Color.LightGray,
    indicatorThickness: Dp = 24.dp,
    // pass the achieved value from api here.
    dataUseage: Float = 60f,
    animationDuration: Int = 1000,
    dataTextStyle: TextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 25.sp
    )
) {
    // It remembers tha data user value
    var dataUseageRemeber by remember {
        mutableStateOf(-1f)
    }

    // animate the forground indicator
    val dataUseageAnimate = animateFloatAsState(
        targetValue = dataUseageRemeber,
        animationSpec = tween(
            durationMillis = animationDuration
        )
    )

    // start animation when activity is opened
    LaunchedEffect(key1 = Unit) {
        dataUseageRemeber = dataUseage
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 120.dp, bottom = 170.dp)) {
        Box(modifier = Modifier
            .size(1180.dp), contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(size)) {
                // for shadow
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(shadowColor, Color.White),
                        center = Offset(this.size.width / 2, y = this.size.height / 2),
                        radius = this.size.height / 2
                    ),
                    radius = this.size.height / 2,
                    center = Offset(x = this.size.width / 2, y = this.size.width / 2)
                )

                // This is where the white circle appears on top of the shadow circle
                drawCircle(
                    color = Color.White,
                    radius = (size / 2 - indicatorThickness).toPx(),
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2)
                )

                val sweepAngle = (dataUseageAnimate.value) * 360 / 100

                // forground color
                drawArc(
                    color = forgroundIndicatorColor,
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round),
                    size = Size(
                        width = (size - indicatorThickness).toPx(),
                        height = (size - indicatorThickness).toPx()
                    ),
                    topLeft = Offset(
                        x = (indicatorThickness / 2).toPx(),
                        y = (indicatorThickness / 2).toPx()
                    )
                )
            }
            // Display the progress value
            DisplayText(
                animateNumber = dataUseageAnimate,
                dataTextStyle = dataTextStyle,
                remainingTextStyle = dataTextStyle
            )
        }
    }


    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 310.dp, top = 780.dp)
    ) {
        ButtonProgressbar {
            dataUseageRemeber = (0 until 100).random().toFloat()
        }
    }






//    Spacer(modifier = Modifier.height(32.dp))

//    ButtonProgressbar {
//        dataUseageRemeber = (0 until 100).random().toFloat()
//    }
}

@Composable
private fun DisplayText(
    animateNumber: State<Float>,
    dataTextStyle: TextStyle,
    remainingTextStyle: TextStyle
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Text that shows the number inside the circle
        Text(
            text = (animateNumber.value).toInt().toString() + " GB",
            style = dataTextStyle
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Remaining",
            style = remainingTextStyle
        )
    }
}

@Composable
private fun ButtonProgressbar(
    backgroundColor: Color = Color(0xFF35898f),
    onClickButton: () -> Unit
) {

        Button(
            onClick = {
                onClickButton()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor
            ),
            modifier = Modifier.width(320.dp).height(70.dp)
        ) {
            Text(
                text = "Animate with Random Value",
                color = Color.White,
                fontSize = 20.sp
            )
        }

}





