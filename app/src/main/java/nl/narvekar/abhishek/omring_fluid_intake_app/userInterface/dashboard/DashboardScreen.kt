package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard

import android.content.SharedPreferences
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.PATIENT_ID
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.UserResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.CircularProgressBar
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.SelectDrinkDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LogDrinkViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel



@Composable
fun DashBoardScreen(
    navController: NavController,
    logDrinkViewModel: LogDrinkViewModel,
    sharedPreferences: SharedPreferences,
    loginViewModel: LoginViewModel
) {
    val showDialog = remember { mutableStateOf(false) }
    val inputValue = remember { mutableStateOf(0.0f) }

    val context = LocalContext.current

    if (showDialog.value) {
        SelectDrinkDialog(logDrinkViewModel, sharedPreferences, setShowDialog = {
            showDialog.value = it
        }) {

            inputValue.value += it
        }
    }

    Scaffold(
        topBar = {
            FluidTopAppBar("Dashboard")
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

                    DashBoardSpinnerAndQuote(inputValue.value.toFloat(), logDrinkViewModel, sharedPreferences)
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
fun DashBoardSpinnerAndQuote(drinkAmount: Float, logDrinkViewModel: LogDrinkViewModel, sharedPreferences: SharedPreferences) {

    val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "").toString()
    Log.d("token at spinner", authToken)
    val patient: UserResponse = logDrinkViewModel.getPatientById(authToken, PATIENT_ID)

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
                if (patient.dailyLimit != null) {
                    CircularProgressBar(percentage = drinkAmount, number = patient.dailyLimit)
                }
                else {
                    CircularProgressBar(drinkAmount, 100)
                }

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
            val image = painterResource(id = R.drawable.message_box)
            Image(painter = image, contentDescription = null)
            Text(text = "A cup a day keeps the doctor away",
                textAlign = TextAlign.Left, fontSize = 29.sp, color = Color.White)
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
fun FluidTopAppBar(dashboardTitle: String) {
    TopAppBar(
        backgroundColor = Color(0xFF1BAEEE),
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.h6) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        text = dashboardTitle,
                        color = Color.White,
                        fontSize = 34.sp
                    )
                }
            }
        }
    }
}

