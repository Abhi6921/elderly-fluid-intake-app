package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.omringButtonColor
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.FluidTopAppBar
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession


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
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "recipe image",
                    modifier = Modifier
                        .size(364.dp)
                        .clip(CircleShape)
                        .border(1.dp, omringButtonColor, CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Text(text = stringResource(id = R.string.profile_photo_text), fontSize = 34.sp)
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
                Text(text = stringResource(id = R.string.phone_number_text), fontSize = 44.sp)
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