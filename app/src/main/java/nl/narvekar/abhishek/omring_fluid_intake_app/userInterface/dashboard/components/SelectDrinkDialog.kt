package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components

import android.content.SharedPreferences
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import nl.narvekar.abhishek.omring_fluid_intake_app.Constants.AUTH_TOKEN_KEY
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LogDrinkViewModel
import kotlin.math.roundToInt
import kotlin.math.roundToLong


@Composable
fun SelectDrinkDialog(
    logDrinkViewModel: LogDrinkViewModel,
    sharedPreferences: SharedPreferences,
    setShowDialog: (Boolean) -> Unit,
    setValue: (Float) -> Unit,
) {
    val authToken = sharedPreferences.getString(AUTH_TOKEN_KEY, "").toString()
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .width(1150.dp)
                .height(400.dp)
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Please select a amount",
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        textAlign = TextAlign.Center
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
                Spacer(modifier = Modifier.height(20.dp))
                Column(Modifier.fillMaxWidth()) {
                    // 100 ml button
                    Box(modifier = Modifier.padding(40.dp, 60.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                // get the drink value which is the amount
                                // do an api call to set the drink value.
                                // store and display the value from api

                            // login details: 3125634121521
                            // password: Tom123!!
                              val drinkAmount = 3
                              val floatAmount = (drinkAmount.toFloat() / 100f)
                              setValue(floatAmount)
                              setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                //.fillMaxWidth()
                                .height(70.dp)
                                .width(500.dp)
                        ) {
                            Text(text = "100ml", fontSize = 24.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // 150 ml button
                    Box(modifier = Modifier.padding(40.dp, 15.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                  val drinkAmount = 6
                                  val floatAmount = (drinkAmount.toFloat() / 100f)
                                  setValue(floatAmount)
                                  setShowDialog(false)

//                                val drinkAmount = 6
//                                setValue(drinkAmount)
//                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .height(70.dp)
                                .width(500.dp)
                        ) {
                            Text(text = "150ml", fontSize = 24.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // 200ml button
                    Box(modifier = Modifier.padding(40.dp, 15.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                val drinkAmount = 12 // replace this value with the value from the api
                                val floatAmount = (drinkAmount.toFloat() / 100f) // replace the 100f with target value of the user
                                setValue(floatAmount)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .width(500.dp)
                        ) {
                            Text(text = "200ml", fontSize = 24.sp)
                        }
                    }

                }
            }
        }
    }
}
