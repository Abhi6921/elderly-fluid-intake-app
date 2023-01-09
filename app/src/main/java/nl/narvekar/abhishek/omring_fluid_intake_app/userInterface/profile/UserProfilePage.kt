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
import androidx.compose.ui.res.stringResource
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
fun UserProfilePage(navController: NavController) {
    val scrollState = rememberScrollState()
    val phoneNumber = AppSession.getPhoneNumber()
    val firstName = AppSession.getFirstName()
    val lastName = AppSession.getLastName()
    val dailyLimit = AppSession.getDailyLimit()
    val dailyGoal = AppSession.getDailyGoal()

    Scaffold(
        topBar = {
            FluidTopAppBar(stringResource(id = R.string.profile_title))
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
                Text(text = stringResource(id = R.string.first_name_text), fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = firstName, fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 593.dp)
            ) {
                Text(text = stringResource(id = R.string.last_name_text), fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = lastName, fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 693.dp)
            ) {
                Text(text = stringResource(id = R.string.phonenumber_text), fontSize = 44.sp)
                Spacer(modifier = Modifier.width(103.dp))
                Text(text = phoneNumber, fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 793.dp)
            ) {
                Text(text = stringResource(id = R.string.daily_limit_label), fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = "${dailyLimit}ml", fontSize = 44.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 39.dp, top = 893.dp)
            ) {
                Text(text = stringResource(id = R.string.daily_goal_label), fontSize = 44.sp)
                Spacer(modifier = Modifier.width(183.dp))
                Text(text = "${dailyGoal}ml", fontSize = 44.sp)
            }
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }

    )

}