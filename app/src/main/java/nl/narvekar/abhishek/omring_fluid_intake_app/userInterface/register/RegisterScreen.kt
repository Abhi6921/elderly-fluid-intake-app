package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register

import android.util.Log
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Role
import nl.narvekar.abhishek.omring_fluid_intake_app.data.UserRequest
import nl.narvekar.abhishek.omring_fluid_intake_app.data.UserRole
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.components.EmptyFieldMessageDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.components.RegisterConfirmDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.components.RegisterFailureDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RegisterViewModel
import kotlin.math.log


@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navController: NavController) {
    val context = LocalContext.current
    val showSuccessDialog = registerViewModel.showSuccessMessage.value
    val showFailureDialog = registerViewModel.showFailureMessage.value
    val showEmptyFieldsDialog = remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        RegisterConfirmDialog(navController)
    }
    if (showFailureDialog) {
        RegisterFailureDialog(registerViewModel.showFailureMessage)
    }

    if (showEmptyFieldsDialog.value) {
        EmptyFieldMessageDialog(showEmptyFieldsDialog)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF39CCFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
//        val firstName by remember { mutableStateOf("") }
//        val lastName by remember { mutableStateOf("") }
//        val email  by remember { mutableStateOf("") }
//        val phoneNumber by remember { mutableStateOf("") }
//        val password by remember { mutableStateOf("") }
//        val passwordVisible by remember { mutableStateOf(false) }
//        val dailyLimit by remember { mutableStateOf("") }
//        val dailygoal by remember { mutableStateOf("") }
//        val dateOfBirth by remember { mutableStateOf("") }

          val firstname = remember { mutableStateOf("") }
          val lastname = remember { mutableStateOf("") }
          val email = remember { mutableStateOf("") }
          val phonenumber = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordVisible = remember { mutableStateOf(false) }
        val dailylimit = remember { mutableStateOf("") }
        val dailygoal = remember { mutableStateOf("") }
        val dateofbirth = remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = firstname.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "personIcon",
                    Modifier
                        .width(60.dp)
                        .height(40.dp)
            ) },
            onValueChange = {
               firstname.value = it
            },
            label = { Text(text = "firstName", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = lastname.value,
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
                lastname.value = it
            },
            label = { Text(text = "lastname", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // email textfield
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = email.value,
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
                email.value= it
            },
            label = { Text(text = "email", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // phone-number text-field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = phonenumber.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                Text(
                    text = "+31",
                    color = Color.Black,
                    fontSize = 27.sp,
                    modifier = Modifier.padding(start = 14.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
            },
            onValueChange = {
                phonenumber.value = it
            },
            label = { Text(text = "Phonenumber", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Password Field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = password.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            visualTransformation = if (passwordVisible.value) { VisualTransformation.None } else { PasswordVisualTransformation() },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp))
                          },
            trailingIcon = {
                  val image = if (passwordVisible.value) {
                      Icons.Default.Visibility
                  }
                else {
                    Icons.Filled.VisibilityOff
                  }
                val description = if(passwordVisible.value) "Hide Password" else "Show Password"
                IconButton(onClick = { passwordVisible.value =! passwordVisible.value }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            onValueChange = {
                password.value = it
            },
            label = { Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center) },
        )
        Spacer(modifier = Modifier.height(10.dp))
        // daily intake
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = dailylimit.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                /*ImageVector.vectorResource(id = R.drawable.target_icon)*/
                Image(painterResource(id = R.drawable.target_icon),
                    contentDescription = "targetIcon")
            },
            onValueChange = {
                dailylimit.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter your daily limit in (L)", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = dailygoal.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                /*ImageVector.vectorResource(id = R.drawable.target_icon)*/
                Image(painterResource(id = R.drawable.target_icon),
                    contentDescription = "targetIcon")
            },
            onValueChange = {
                dailygoal.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter your daily goal in (L)", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = dateofbirth.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                  Image(imageVector = Icons.Default.CalendarToday, contentDescription = null)
            },
            onValueChange = {
                dateofbirth.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            label = { Text(text = "Enter your date of birth", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(40.dp))
        Log.d("firstNameOutput", "$firstname")
        // RegisterButton
        Button(
            onClick = {
                if(firstname.value.isEmpty()
                    || lastname.value.isEmpty()
                    || email.value.isEmpty()
                    || phonenumber.value.isEmpty()
                    || password.value.isEmpty()
                    || dailygoal.value.isEmpty()
                    || dailylimit.value.isEmpty()
                ) {
                    showEmptyFieldsDialog.value = true
                }
                else {
                    val firstName: String = firstname.value.toString()
                    val lastName: String = lastname.value.toString()
                    val emailId: String = email.value.toString()
                    val phoneNumber: String = phonenumber.value.toString()
                    val Password: String = password.value.toString()
                    val Dailylimit: Int = Integer.parseInt(dailylimit.value)
                    val DailyGoal: Int = Integer.parseInt(dailygoal.value)
                    val DateOfBirth: String = dateofbirth.value.toString()

                    registerViewModel.registerUser(
                        context,
                        UserRequest(
                            firstName,
                            lastName,
                            emailId,
                            "+31${phoneNumber}",
                            Password,
                            true,
                            Dailylimit,
                            null,
                            null,
                            UserRole("PATIENT"),
                            DailyGoal,
                            DateOfBirth,
                            "00000000-0000-0000-0000-000000000000"
                        )
                    )
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