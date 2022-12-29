package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.PatientResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.UserResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.CircularProgressBar
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.SelectDrinkDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LogDrinkViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel
import kotlin.math.log


@SuppressLint("SuspiciousIndentation")
@Composable
fun DashBoardScreen(
    navController: NavController,
    logDrinkViewModel: LogDrinkViewModel,
    loginViewModel: LoginViewModel,
    patientViewModel: PatientViewModel
) {
    val showDialog = remember { mutableStateOf(false) }
    val phoneNumber = AppSession.getPhoneNumber()

    val patient = patientViewModel.getPatientByPhoneNumber(phoneNumber)

    if (showDialog.value) {
        SelectDrinkDialog(
            logDrinkViewModel,
            patientViewModel,
            setShowDialog = {
                showDialog.value = it
        }, setValue = { })
    }
    else {
        SetCircularProgress(patientViewModel = patientViewModel)
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
                Text(text = "Welcome ${patient?.firstName} ${patient?.lastName}", textAlign = TextAlign.Center, fontSize = 35.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Today's goal: ${patient?.dailyLimit} ml", textAlign = TextAlign.Center, fontSize = 35.sp)
                val logDrinkResponse = patientViewModel.logDrinkResponse.Achieved.toString()
                Text(text = "Achieved: $logDrinkResponse", textAlign = TextAlign.Center, fontSize = 35.sp)
                Spacer(modifier = Modifier.height(60.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                        SetCircularProgress(patientViewModel)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 290.dp, horizontal = 100.dp)

                    ) {
                        // Fluid Intake Button
                        Column(
                            modifier = Modifier
                                .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
                                .width(270.dp)
                                .height(490.dp)
                                .clickable {
                                    showDialog.value = true
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Spacer(modifier = Modifier.height(40.dp))
                            Image(
                                painter = painterResource(R.drawable.water_intake),
                                contentDescription = "water intake image",
                                modifier = Modifier.width(200.dp).height(230.dp)
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(text = "Fluid Intake", fontSize = 35.sp)
                        }
                        Spacer(modifier = Modifier.width(34.dp))
                        // Recipe button
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
    LogoutButton(navController, loginViewModel)

}

@Composable
fun SetCircularProgress(patientViewModel: PatientViewModel) {
    val phoneNumber = AppSession.getPhoneNumber()
    val patient = patientViewModel.getPatientByPhoneNumber(phoneNumber)

    val logDrinkResponse = patientViewModel.getCurrentFluidIntakeStatus(patient?.id.toString())
    logDrinkResponse.Achieved?.toFloat()
        ?.let { achieved -> logDrinkResponse.DailyLimit?.toInt()
            ?.let { dailyLimit -> DashBoardSpinnerAndQuote(achieved, dailyLimit) } }

}

@Composable
fun DashBoardSpinnerAndQuote(drinkAmount: Float, dailyLimit: Int) {
    Log.d("dashbord-drinkAmount ", drinkAmount.toString())
    Log.d("dashbord-dailyLimit ", dailyLimit.toString())


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
        )
        {
            if (drinkAmount.toFloat() == null) {
                CircularProgressBar(0.0f, dailyLimit)
            }
            CircularProgressBar(drinkAmount, dailyLimit)
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
            .size(250.dp)
            .padding(0.dp)
        ) {
            val image = painterResource(id = R.drawable.message_box)
            Image(painter = image, contentDescription = null)
            Text(
                text = "A cup a day keeps the doctor away",
                textAlign = TextAlign.Center, fontSize = 29.sp,
                color = Color.White
            )
        }
    }

}

@Composable
fun LogoutButton(
    navController: NavController,
    loginViewModel: LoginViewModel
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
                //todo 3. clear the value of drinkAmoutnt from shared preferences (set to 0)
                loginViewModel.logout(navController)
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



