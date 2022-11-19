package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.CircularProgressBar
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.SelectDrinkDialog


@Preview(showBackground = true, widthDp = 900, heightDp = 1180)
@Composable
fun DashBoardScreen() {
    val showDialog = remember { mutableStateOf(false) }

    var inputValue = remember { mutableStateOf(0.0f) }

    if (showDialog.value) {
        SelectDrinkDialog(value = "", setShowDialog = {
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
            AppBottomNav()
        },
        content = {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Today's goal: 800ml", textAlign = TextAlign.Center, fontSize = 35.sp)
            }
            //Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                if(inputValue.value.equals(1.0f)) {
                    inputValue.value = 0.0f
                }
                CircularProgressBar(percentage = inputValue.value, number = 100)
                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                       painter = painterResource(R.drawable.rain_drop),
                       contentDescription = "drop emoji",
                       alignment = Alignment.Center,
                       contentScale = ContentScale.Fit,
                       modifier = Modifier.size(160.dp)
                    )
                    Text(text = "A cup a day keeps doctor away", fontSize = 25.sp, textAlign = TextAlign.End)
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(45.dp)
                ) {
                    // Fluid Intake Button
                    Column(modifier = Modifier
                        .border(BorderStroke(5.dp, Color(0xFF1B7D71)))
                        .width(270.dp)
                        .height(380.dp)
                        .clickable {
                            showDialog.value = true
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

            }
        }
    )
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

            navigationIcon = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                }
            }, actions = {
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

