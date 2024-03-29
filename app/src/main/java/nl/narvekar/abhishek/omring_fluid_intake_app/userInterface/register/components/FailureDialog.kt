package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import nl.narvekar.abhishek.omring_fluid_intake_app.R


@Composable
fun RegisterFailureDialog(showMessage: MutableState<Boolean>) {

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(modifier = Modifier.padding(35.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    //horizontalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter =
                    painterResource(R.drawable.failure),
                        contentDescription = "failure image icon"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = stringResource(id = R.string.register_failure_string), fontSize = 34.sp)
                    Text(text = stringResource(id = R.string.register_failure_description), fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            showMessage.value = false
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
                        modifier = Modifier
                            .height(49.dp)
                            .width(322.dp)
                    ) {
                        Text(text = stringResource(id = R.string.ok_text), color = Color.White, fontSize = 25.sp)
                    }
                }
            }
        }
    }
}