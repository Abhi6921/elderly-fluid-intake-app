package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.narvekar.abhishek.omring_fluid_intake_app.R

@Preview(showBackground = true, widthDp = 1180, heightDp = 1200)
@Composable
fun RegisterScreen() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF39CCFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(R.drawable.omring_logo),
            contentDescription = "Omring logo",
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Welcome Onboard!",
            fontSize = 35.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Let's create an account",
            fontSize = 15.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        var fullName by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var targetIntake by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = fullName,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "personIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp)
            ) },
            onValueChange = {
                fullName = it
            },
            label = { Text(text = "Fullname", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = phoneNumber,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "personIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp)
            ) },
            onValueChange = {
                phoneNumber = it
            },
            label = { Text(text = "Phonenumber", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = targetIntake,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                            /*ImageVector.vectorResource(id = R.drawable.target_icon)*/
                            Image(painterResource(id = R.drawable.target_icon),
                                contentDescription = "targetIcon")
                          },
            onValueChange = {
                targetIntake = it
            },
            label = { Text(text = "Enter your intake in (L)", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Username Field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = targetIntake,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "personIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp)
            ) },
            onValueChange = {
                username = it
            },
            label = { Text(text = "username", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Password Field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = password,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            visualTransformation = if (passwordVisible) { VisualTransformation.None } else { PasswordVisualTransformation() },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp))
                          },
            trailingIcon = {
                  val image = if (passwordVisible) {
                      Icons.Filled.Visibility
                  }
                else {
                    Icons.Filled.VisibilityOff
                  }

                val description = if(passwordVisible) "Hide Password" else "Show Password"
            },
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center) },
        )
        Spacer(modifier = Modifier.height(35.dp))
        // RegisterButton
        Button(
            onClick = {
                /*Navigate to Success Screen*/
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
        ) {
            Text(text = "Login here", color = Color.White, fontSize = 30.sp)
        }


    }
}
/*
    Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(R.drawable.omring_logo),
            contentDescription = "Omring logo",
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )
*/