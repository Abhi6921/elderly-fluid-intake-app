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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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

//@Preview(showBackground = true, widthDp = 900, heightDp = 1280)
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val context = LocalContext.current

    val showSuccessDialog = registerViewModel.showSuccessMessage.value
    val showFailureDialog = registerViewModel.showFailureMessage.value
    val showEmptyFieldsDialog = remember { mutableStateOf(false) }

    val isProcessing = registerViewModel.isProcessing.value

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
            text = stringResource(id = R.string.welcome_text_string),
            fontSize = 35.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(id = R.string.create_account_text),
            fontSize = 25.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

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
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .size(45.dp)
            ) },
            onValueChange = {
               firstname.value = it
            },
            placeholder = { Text(text = stringResource(id = R.string.firstname_text), fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) }
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
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .size(45.dp)
            ) },
            onValueChange = {
                lastname.value = it
            },
            placeholder = { Text(text = stringResource(id = R.string.lastname_text), fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) }
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
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .size(40.dp)
            ) },
            onValueChange = {
                email.value= it
            },
            placeholder = { Text(text = stringResource(id = R.string.email_text), fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp, top = 10.dp, bottom = 10.dp)) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        // phone-number text-field
        val maxNumbers = 10
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
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 14.dp, top = 10.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
            },
            onValueChange = {
                if(it.length <= maxNumbers) phonenumber.value = it
            },
            placeholder = { Text(text = stringResource(id = R.string.phonenumber_label), fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) }
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
                modifier = Modifier
                    .padding(start = 14.dp, top = 10.dp, bottom = 10.dp)
                    .size(40.dp))
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
                    Icon(imageVector = image, contentDescription = "password", modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                        .size(35.dp))
                }
            },
            onValueChange = {
                password.value = it
            },
            placeholder = { Text(text = stringResource(id = R.string.password_label), fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) },
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
                    contentDescription = "targetIcon",
                    modifier = Modifier
                        .padding(start = 14.dp, top = 10.dp, bottom = 10.dp)
                        .size(40.dp)
                )
            },
            onValueChange = {
                dailylimit.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = stringResource(id = R.string.dailylimit_text), fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) }
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
                Image(painterResource(id = R.drawable.target_icon),
                    contentDescription = "targetIcon",
                    modifier = Modifier
                        .padding(start = 14.dp, top = 10.dp, bottom = 10.dp)
                        .size(40.dp)
                )
            },
            onValueChange = {
                dailygoal.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = stringResource(id = R.string.dailygoal_text), fontSize = 20.sp,  modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) }
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
                  Image(imageVector = Icons.Default.CalendarToday, contentDescription = "calendar", modifier = Modifier
                      .padding(start = 14.dp, top = 10.dp, bottom = 10.dp)
                      .size(40.dp))
            },
            onValueChange = {
                dateofbirth.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = stringResource(id = R.string.dateofbirth_text), fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) }
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 300.dp)) {
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
            Spacer(modifier = Modifier.width(60.dp))
            if (isProcessing) {
                CircularProgressIndicator(modifier = Modifier.then(Modifier.size(62.dp)), color = Color.White)
            }
        }
    }
}