package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.User
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.components.RegisterConfirmDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RegisterViewModel


@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navController: NavController) {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF39CCFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (showDialog.value) {
            RegisterConfirmDialog(setShowDialog = {
                showDialog.value = it
            }, navController)
        }

        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(R.drawable.omring_logo),
            contentDescription = "Omring logo",
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome Onboard!",
            fontSize = 35.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Let's create an account",
            fontSize = 25.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email  by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var dailyLimit by remember { mutableStateOf("") }
        var dailygoal by remember { mutableStateOf("") }
        var dateOfBirth by remember { mutableStateOf("") }

        // firstname textfield
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = firstName,
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
               firstName = it
            },
            label = { Text(text = "firstName", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))

        // lastname field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = lastName,
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
                lastName = it
            },
            label = { Text(text = "lastname", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // email textfield
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = email,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "emailIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp)
            ) },
            onValueChange = {
                email= it
            },
            label = { Text(text = "email", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // phone-number text-field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = phoneNumber,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "phoneIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp)
            ) },
            onValueChange = {
                phoneNumber = it
            },
            label = { Text(text = "Phonenumber", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))

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
                      Icons.Default.Visibility
                  }
                else {
                    Icons.Filled.VisibilityOff
                  }
                val description = if(passwordVisible) "Hide Password" else "Show Password"
                IconButton(onClick = { passwordVisible =!passwordVisible }) {
                    Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
                }
            },
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center) },
        )
        Spacer(modifier = Modifier.height(10.dp))
        // daily intake
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = dailyLimit,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                /*ImageVector.vectorResource(id = R.drawable.target_icon)*/
                Image(painterResource(id = R.drawable.target_icon),
                    contentDescription = "targetIcon")
            },
            onValueChange = {
                dailyLimit = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter your daily limit in (L)", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = dailygoal,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                /*ImageVector.vectorResource(id = R.drawable.target_icon)*/
                Image(painterResource(id = R.drawable.target_icon),
                    contentDescription = "targetIcon")
            },
            onValueChange = {
                dailygoal = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter your daily goal in (L)", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = dateOfBirth,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                  Image(imageVector = Icons.Default.CalendarToday, contentDescription = null)
            },
            onValueChange = {
                dateOfBirth = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter your date of birth", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(40.dp))
        // RegisterButton
        Button(
            onClick = {
                if(firstName.isEmpty()
                    || lastName.isEmpty()
                    || email.isEmpty()
                    || phoneNumber.isEmpty()
                    || password.isEmpty()
                    || dailygoal.isEmpty()
                    || dailyLimit.isEmpty()
                ) {
                    Toast.makeText(context, "Please fill all the credentials!", Toast.LENGTH_LONG).show()
                }
                else {
                    registerViewModel.signUpUser(
                        context,
                        User(firstName,
                            lastName,
                            email,
                            phoneNumber,
                            password,
                            null,
                            dailyLimit.toInt(),
                            null,
                            dailygoal.toInt(),
                            dateOfBirth
                        ))

                    showDialog.value = true
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
        ) {
            Text(text = "Register", color = Color.White, fontSize = 30.sp)
        }
    }
}