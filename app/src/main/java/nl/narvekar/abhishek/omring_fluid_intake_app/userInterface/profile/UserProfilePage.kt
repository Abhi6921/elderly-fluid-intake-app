package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.FluidTopAppBar
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.LogoutButton
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel


@Composable
fun UserProfilePage(navController: NavController, loginViewModel: LoginViewModel, patientViewModel: PatientViewModel) {
    val scrollState = rememberScrollState()
    val phoneNumber = AppSession.getPhoneNumber()
    val patient = patientViewModel.getPatientByPhoneNumber(phoneNumber)

    Scaffold(
        topBar = {
            FluidTopAppBar("Profile")
        },
        content = { innterPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(1.dp, Color.Red, RectangleShape)
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    //model = R.drawable.recipe_img,
                    painter = painterResource(id =R.drawable.profile),
                    contentDescription = "recipe image",
                    modifier = Modifier
                        .width(350.dp)
                        .height(350.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 493.dp)
            ) {
                Text(text = "FirstName:", fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = "${patient?.firstName}", fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 593.dp)
            ) {
                Text(text = "Lastname: ", fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = "${patient?.lastName}", fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 693.dp)
            ) {
                Text(text = "PhoneNumber:", fontSize = 44.sp)
                Spacer(modifier = Modifier.width(103.dp))
                Text(text = "${patient?.phoneNumber}", fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 793.dp)
            ) {
                Text(text = "DailyLimit: ", fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = "${patient?.dailyLimit}ml", fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 893.dp)
            ) {
                Text(text = "DailyGoal: ", fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = "${patient?.dailyGoal}ml", fontSize = 44.sp)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(880.dp))
                Button(
                    onClick = {
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
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }

    )

}