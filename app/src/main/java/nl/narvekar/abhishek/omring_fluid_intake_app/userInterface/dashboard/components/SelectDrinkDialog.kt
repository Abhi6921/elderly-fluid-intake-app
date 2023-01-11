package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components

import nl.narvekar.abhishek.omring_fluid_intake_app.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.omringButtonColor
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.SetCircularProgress
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.HUNDRED_ML_INTAKE
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.ONE_FIFTY_ML_INTAKE
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.Constants.TWO_HUNDRED_ML_INTAKE
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LogDrinkViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel



//@Preview(showBackground = true, widthDp = 900, heightDp = 1280)
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
                .width(1100.dp)
                .height(650.dp)
        ) {
            Box(modifier = Modifier.padding(start = 30.dp, end = 10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text =  stringResource(id = R.string.dialog_title),
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(start = 50.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(imageVector = Icons.Filled.Cancel,
                        contentDescription = "close button",
                        tint = colorResource(android.R.color.darker_gray),
                        modifier = Modifier
                            .width(50.dp)
                            .height(60.dp)
                            .clickable { setShowDialog(false) }
                    )
                }

                Column(modifier = Modifier.padding(end = 20.dp)) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.select_amount_text),
                        modifier = Modifier.padding(start = 40.dp, top = 90.dp, end = 30.dp, bottom = 0.dp),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                    // 100 ml button
                    Box(modifier = Modifier.padding(start = 20.dp, top = 50.dp, bottom = 0.dp)) {
                        Button(
                            onClick = {
                                //val drinkAmount = 100
                                logDrinkViewModel.postANewDrink(context, HUNDRED_ML_INTAKE)
                                setShowDialog(false)
                                navController.navigate(Routes.Home.route)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                //.fillMaxWidth()
                                .height(100.dp)
                                .width(500.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007B85))
                        ) {
                            Icon(painter = painterResource(R.drawable.cup_icon),
                                contentDescription = null, 
                                modifier = Modifier
                                    .size(130.dp)
                                    .padding(end = 60.dp),
                                tint = Color.White
                            )
                            Text(text = stringResource(id = R.string.button_100ml_text), fontSize = 34.sp, modifier = Modifier.padding(end = 60.dp), color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    // 150 ml button
                    Box(modifier = Modifier.padding(start = 20.dp, top = 15.dp, bottom = 0.dp)) {
                        Button(
                            onClick = {
                                  //val drinkAmount = 150
                                  logDrinkViewModel.postANewDrink(context, ONE_FIFTY_ML_INTAKE)
                                  setShowDialog(false)
                                  navController.navigate(Routes.Home.route)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .height(100.dp)
                                .width(500.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007B85))
                        ) {
                            Icon(painter = painterResource(R.drawable.cup_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(130.dp)
                                    .padding(end = 60.dp),
                                tint = Color.White
                            )
                            Text(text = stringResource(id = R.string.button_150ml_text), fontSize = 34.sp, modifier = Modifier.padding(end = 60.dp), color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    // 200ml button
                    Box(modifier = Modifier.padding(start = 20.dp,top = 15.dp, end = 5.dp,bottom = 0.dp)) {
                        Button(
                            onClick = {
                                //val drinkAmount = 200
                                logDrinkViewModel.postANewDrink(context, TWO_HUNDRED_ML_INTAKE)
                                setShowDialog(false)
                                navController.navigate(Routes.Home.route)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .width(500.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007B85))
                        ) {
                            Icon(painter = painterResource(R.drawable.cup_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(130.dp)
                                    .padding(end = 60.dp),
                                tint = Color.White
                            )
                            Text(text = stringResource(id = R.string.button_200ml_text), fontSize = 34.sp, modifier = Modifier.padding(end = 60.dp), color = Color.White)
                        }
                    }

                }
            }
        }
    }
}
