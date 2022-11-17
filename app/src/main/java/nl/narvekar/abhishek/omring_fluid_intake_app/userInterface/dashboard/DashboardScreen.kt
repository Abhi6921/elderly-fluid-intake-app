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

@Preview(showBackground = true, widthDp = 774, heightDp = 800)
@Composable
fun DashBoardScreen() {
    val gradientBlueWhite = Brush.verticalGradient(0f to Color(0xFF1BAEEE), 1000f to Color(
        0xFFFFFFFF
    )
    )
//    Row(modifier = Modifier
//        .fillMaxWidth()
//        .background(gradientBlueWhite)
//        .height(80.dp),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        Column() {
//            Spacer(modifier = Modifier.height(20.dp))
//            Text(
//                text = "Dashboard",
//                fontSize = 22.sp,
//                color = Color(0xFF1B7D71),
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }

    //Spacer(modifier = Modifier.width(70.dp))
    
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(gradientBlueWhite)
            .height(80.dp)) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = "Dashboard",
                    fontSize = 32.sp,
                    color = Color(0xFF1B7D71),
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
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
        //Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Today's goal: 800ml", textAlign = TextAlign.Right, fontSize = 35.sp)
       }
        Spacer(modifier = Modifier.height(70.dp))
       Row {
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

        Column(modifier = Modifier
            .border(BorderStroke(5.dp, Color.Red))
            .width(320.dp)
            .height(390.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.omring_logo),
                contentDescription = ""
            )
        }

//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.Top
//            ) {
//                OutlinedButton(
//                    onClick = {
//                        // navigate to fluid intake popup
//                    },
//                    border = BorderStroke(5.dp, Color.Red),
//                    modifier = Modifier.height(320.dp)
//
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Top
//                    ) {
//                        Image(
//                            painter = painterResource(R.drawable.omring_logo),
//                            contentDescription = "random icon"
//                        )
//                    }
//
//                    Text(text = "Fluid Intake", fontSize = 35.sp)
//
//                }
//                Spacer(modifier = Modifier.width(50.dp))
//                OutlinedButton(
//                    onClick = {
//                        // navigate to fluid intake popup
//                    },
//                    border = BorderStroke(5.dp, Color.Red)
//                ) {
//                    Column(Modifier.height(290.dp)) {
//                        Image(
//                            painter = painterResource(R.drawable.target_icon),
//                            contentDescription = "random icon"
//                        )
//                    }
//
//                    Text(text = "Fluid Intake", textAlign = TextAlign.Center, fontSize = 35.sp)
//                }
//            }
//        }
    }
}
