package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav


@Composable
fun DashBoardScreen() {
    val gradientBlueWhite = Brush.verticalGradient(
        0f to Color(0xFF1BAEEE),
        1000f to Color(0xFFFFFFFF)
    )
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            //.background(gradientBlueWhite)
            .height(380.dp)) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Text(
                    text = "Dashboard",
                    fontSize = 32.sp,
                    color = Color(0xFF1B7D71),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(80.dp))
                Text(text = "Today's goal: 800ml", textAlign = TextAlign.Start, fontSize = 35.sp)
            }

            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                Image(
                    painter = painterResource(id = R.drawable.omring_logo),
                    contentDescription = "user profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
            }
        }
       Row(verticalAlignment = Alignment.Top,
           horizontalArrangement = Arrangement.Center) {
           Image(
               painter = painterResource(R.drawable.progressstate),
               contentDescription = "progress state",
               alignment = Alignment.Center,
               contentScale = ContentScale.Fit,
               modifier = Modifier.size(200.dp)
           )
           Spacer(modifier = Modifier.width(20.dp))
           Image(
               painter = painterResource(R.drawable.rain_drop),
               contentDescription = "drop emoji",
               alignment = Alignment.Center,
               contentScale = ContentScale.Fit,
               modifier = Modifier.size(160.dp)
           )
           Text(text = "Chat box will be here", fontSize = 35.sp)
       }
       Spacer(modifier = Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            // Fluid Intake Button
            Column(modifier = Modifier
                .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
                .width(270.dp)
                .height(390.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(R.drawable.water_intake),
                    contentDescription = "water intake image"
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Fluid Intake", fontSize = 35.sp)
            }
            Spacer(modifier = Modifier.width(30.dp))
            // Recipe Button
            Column(modifier = Modifier
                .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
                .width(270.dp)
                .height(390.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(R.drawable.recipe),
                    contentDescription = "water intake image"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Recipes", fontSize = 35.sp)
            }
        }
        Spacer(modifier = Modifier.height(150.dp))
        AppBottomNav()
    }
}
