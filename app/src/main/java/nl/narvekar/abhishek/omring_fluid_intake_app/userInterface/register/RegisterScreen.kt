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
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.register.components.RegisterConfirmDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.RegisterViewModel
import kotlin.math.log


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
//        val firstName by remember { mutableStateOf("") }
//        val lastName by remember { mutableStateOf("") }
//        val email  by remember { mutableStateOf("") }
//        val phoneNumber by remember { mutableStateOf("") }
//        val password by remember { mutableStateOf("") }
//        val passwordVisible by remember { mutableStateOf(false) }
//        val dailyLimit by remember { mutableStateOf("") }
//        val dailygoal by remember { mutableStateOf("") }
//        val dateOfBirth by remember { mutableStateOf("") }

          val firstName = remember { mutableStateOf(TextFieldValue()) }
          val lastName = remember { mutableStateOf(TextFieldValue()) }
          val email = remember { mutableStateOf(TextFieldValue()) }
          val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val passwordVisible = remember { mutableStateOf(false) }
        val dailyLimit = remember { mutableStateOf(TextFieldValue()) }
        val dailygoal = remember { mutableStateOf(TextFieldValue()) }
        val dateOfBirth = remember { mutableStateOf(TextFieldValue()) }




        // firstname textfield
        // 31542698753
        // aram123!!
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = firstName.value,
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
               firstName.value = it
            },
            label = { Text(text = "firstName", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // 3125634121521
        // aram123!!
        // lastname field
        // 1965-04-04T00:00:00
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = lastName.value,
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
                lastName.value = it
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
            value = phoneNumber.value,
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
                phoneNumber.value = it
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
                    Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
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
            value = dailyLimit.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                /*ImageVector.vectorResource(id = R.drawable.target_icon)*/
                Image(painterResource(id = R.drawable.target_icon),
                    contentDescription = "targetIcon")
            },
            onValueChange = {
                dailyLimit.value = it
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
            value = dateOfBirth.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),

            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                  Image(imageVector = Icons.Default.CalendarToday, contentDescription = null)
            },
            onValueChange = {
                dateOfBirth.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            label = { Text(text = "Enter your date of birth", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(40.dp))
        Log.d("firstNameOutput", "$firstName")
        // RegisterButton
        Button(
            onClick = {
                if(firstName.value.text.isEmpty()
                    || lastName.value.text.isEmpty()
                    || email.value.text.isEmpty()
                    || phoneNumber.value.text.isEmpty()
                    || password.value.text.isEmpty()
                    || dailygoal.value.text.isEmpty()
                    || dailyLimit.value.text.isEmpty()
                ) {
                    Toast.makeText(context, "Please fill all the credentials!", Toast.LENGTH_LONG).show()
                }
                else {
                    registerViewModel.signUpUser(
                        context,
//                                UserRequest(
//                                     "Aram4",
//                                         "Jones2",
//                             "aram4.jones@gmail.com",
//                             "+312323179754",
//                             "Aram12345!",
//                             true,
//                             1000,
//                             null,
//                            null,
//                                    UserRole("PATIENT"),
//                             1000,
//                             "1965-04-04T00:00:00",
//                             "10000000-0000-0000-0000-000000000001"
//                          )

                        UserRequest(
                            firstName.value.toString(),
                            lastName.value.toString(),
                            email.value.toString(),
                            phoneNumber.value.toString(),
                            password.value.toString(),
                            true,
                            dailyLimit.value.text.toInt(),
                            null,
                            null,
                            UserRole("PATIENT"),
                            dailygoal.value.text.toInt(),
                            dateOfBirth.value.toString(),
                            "00000000-0000-0000-0000-000000000000"
                        )
                    )

                    //showDialog.value = true
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