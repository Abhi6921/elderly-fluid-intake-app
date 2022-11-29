package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.CircularProgressBar
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.SelectDrinkDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LogDrinkViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




@Composable
fun DashBoardScreen(
    navController: NavController,
    logDrinkViewModel: LogDrinkViewModel,
    sharedPreferences: SharedPreferences,
    loginViewModel: LoginViewModel
) {
    val showDialog = remember { mutableStateOf(false) }
    // change this value and get it from the api
//    amountInFloat = (logDrinkViewModel.drankNow.value.toFloat() / logDrinkViewModel.amountLeftToLimit.value.toFloat())
    var inputvalue = 0.0f
    var inputValue = remember { mutableStateOf(inputvalue) }


    if (showDialog.value) {
        SelectDrinkDialog(logDrinkViewModel, sharedPreferences, setShowDialog = {
            showDialog.value = it
        }) {
            inputValue.value += it
        }
    }

    Scaffold(
        topBar = {
            FluidTopAppBar()
        },
        bottomBar = {
            AppBottomNav(navController)
        },
        content = {
            Column(modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Today's goal: 800ml", textAlign = TextAlign.Center, fontSize = 35.sp)
                Spacer(modifier = Modifier.height(60.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {

                    DashBoardSpinnerAndQuote(inputValue.value.toFloat())
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 300.dp, horizontal = 100.dp)
                            //.safeContentPadding()
                    ) {
                        // Fluid Intake Button
                        Column(
                            modifier = Modifier
                                .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
                                .width(270.dp)
                                .height(380.dp)
                                .clickable {
                                    showDialog.value = true
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Spacer(modifier = Modifier.height(40.dp))
                            Image(
                                painter = painterResource(R.drawable.water_intake),
                                contentDescription = "water intake image"
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Fluid Intake", fontSize = 35.sp)
                        }
                        Spacer(modifier = Modifier.width(34.dp))
                        Column(
                            modifier = Modifier
                                .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
                                .width(270.dp)
                                .height(380.dp)
                                .clickable {
                                    navController.navigate(Routes.Recipes.route) {
                                        popUpTo(Routes.Home.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(R.drawable.recipe),
                                contentDescription = " recipe image"
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Recipes", fontSize = 35.sp)
                        }
                    }
                }
            }
        }
    )
    LogoutButton(navController, loginViewModel, sharedPreferences)

}

@Composable
fun DashBoardSpinnerAndQuote(drinkAmount: Float) {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
                ) {
            CircularProgressBar(percentage = drinkAmount, number = 100)
        }
        Box(modifier = Modifier
            .size(200.dp)
            .padding(10.dp)
            ) {
            Image(
                painter = painterResource(R.drawable.rain_drop),
                contentDescription = "drop emoji",
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(160.dp)
            )
        }
        Box(modifier = Modifier
            .size(200.dp)
            .padding(0.dp)
        ) {

            Text(text = "A cup a day keeps the doctor away",
                textAlign = TextAlign.Left, fontSize = 29.sp)
        }
    }

}

@Composable
fun LogoutButton(
    navController: NavController,
    loginViewModel: LoginViewModel,
    sharedPreferences: SharedPreferences
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(850.dp))
        Button(
            onClick = {
                loginViewModel.logout(navController, sharedPreferences)
            },
            modifier = Modifier
                .height(80.dp)
                .width(220.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71))
        ) {
            Text(text = "Logout", fontSize = 35.sp, color = Color.White)
        }

    }
}
@Composable
fun DashBoardButtons() {
        // Fluid Intake Button
        Column(modifier = Modifier
            .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
            .width(270.dp)
            .height(380.dp)
            .clickable {
                //showDialog.value = true
            },
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
        Spacer(modifier = Modifier.width(40.dp))
        // Recipe Button
        Column(modifier = Modifier
            .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
            .width(270.dp)
            .height(390.dp)
            .clickable {
//                navController.navigate(Routes.Recipes.route) {
//                    popUpTo(Routes.Home.route) {
//                        inclusive = true
//                    }
//                }
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(R.drawable.recipe),
                contentDescription = " recipe image"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Recipes", fontSize = 35.sp)
        }


}

@Composable
fun FluidTopAppBar() {
    val gradientBlueWhite = Brush.verticalGradient(
        0f to Color(0xFF1BAEEE),
        1000f to Color(0xFFFFFFFF)
    )
    Column {
        TopAppBar(
            elevation = 5.dp,
            title = {
                Text("DashBoard", color = Color.White)
            },
            backgroundColor =  Color(0xFF1BAEEE),
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.omring_logo),
                    contentDescription = "user profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
            })
    }
}

