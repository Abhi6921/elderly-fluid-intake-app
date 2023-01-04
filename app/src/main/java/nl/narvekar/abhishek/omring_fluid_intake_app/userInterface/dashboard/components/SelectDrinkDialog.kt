package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components

import nl.narvekar.abhishek.omring_fluid_intake_app.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrink
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.SetCircularProgress
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LogDrinkViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel



@Composable
fun SelectDrinkDialog(
    logDrinkViewModel: LogDrinkViewModel,
    navController: NavController,
    setShowDialog: (Boolean) -> Unit,
) {

    val context = LocalContext.current
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .width(2200.dp)
                .height(600.dp)
        ) {
            Box(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text =  stringResource(id = R.string.select_amount_text),
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Filled.Cancel,
                        contentDescription = "close button",
                        tint = colorResource(android.R.color.darker_gray),
                        modifier = Modifier
                            .width(50.dp)
                            .height(60.dp)
                            .clickable { setShowDialog(false) }
                    )
                }

                Column() {
                    // 100 ml button
                    Box(modifier = Modifier.padding(40.dp, 150.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                val drinkAmount = 100
                                logDrinkViewModel.postANewDrink(context, drinkAmount)
                                setShowDialog(false)
                                navController.navigate(Routes.Home.route)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                //.fillMaxWidth()
                                .height(70.dp)
                                .width(500.dp)
                        ) {
                            Text(text = stringResource(id = R.string.button_100ml_text), fontSize = 24.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    // 150 ml button
                    Box(modifier = Modifier.padding(40.dp, 15.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                  val drinkAmount = 150
                                  logDrinkViewModel.postANewDrink(context, drinkAmount)
                                  setShowDialog(false)
                                  navController.navigate(Routes.Home.route)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .height(70.dp)
                                .width(500.dp)
                        ) {
                            Text(text = stringResource(id = R.string.button_150ml_text), fontSize = 24.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    // 200ml button
                    Box(modifier = Modifier.padding(40.dp, 15.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                val drinkAmount = 200
                                logDrinkViewModel.postANewDrink(context, drinkAmount)
                                setShowDialog(false)
                                navController.navigate(Routes.Home.route)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .width(500.dp)
                        ) {
                            Text(text = stringResource(id = R.string.button_200ml_text), fontSize = 24.sp)
                        }
                    }

                }
            }
        }
    }
}
