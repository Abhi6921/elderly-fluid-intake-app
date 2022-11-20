package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes

//@Preview(showBackground = true, widthDp = 1280, heightDp = 1500, backgroundColor = (0x39CCFF))
@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF39CCFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(R.drawable.omring_logo),
            contentDescription = "Omring logo",
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(R.drawable.start_image),
            contentDescription = "start screen image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Already have an account",
            fontSize = 35.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
        //Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Start.route) {
                        inclusive = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
            modifier = Modifier
                .height(90.dp)
                .width(395.dp)
        ) {
            Text("Log In", color = Color.White, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(90.dp))
        Text(
            text = "New Member",
            fontSize = 35.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                navController.navigate(Routes.Register.route) {
                    popUpTo(Routes.Start.route) {
                        inclusive = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
            modifier = Modifier
                .height(80.dp)
                .width(395.dp)
        ) {
            Text(text = "Register Here", color = Color.White, fontSize = 30.sp)
        }
    }
}