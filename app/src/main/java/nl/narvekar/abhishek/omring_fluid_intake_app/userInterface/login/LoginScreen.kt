package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.components.EmptyFieldMessageDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login.components.LoginFailureDialog
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel


@Composable
fun LoginUI(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {

    val context = LocalContext.current

    val showEmptyFieldDialog = remember { mutableStateOf(false) }
    val showLoginFailureDialog = loginViewModel.showLoginFailureDialog.value

    val isAuthenticating = loginViewModel.isLoading.value

    if (showEmptyFieldDialog.value) {
        EmptyFieldMessageDialog(showEmptyFieldDialog)
    }

    if (showLoginFailureDialog) {
        LoginFailureDialog(showLoginFailureDialog = loginViewModel.showLoginFailureDialog)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF39CCFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(R.drawable.omring_logo),
            contentDescription = "Omring logo",
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(R.drawable.login_image),
            contentDescription = "login screen image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.login_title_text),
            fontSize = 35.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))

        var phonenumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        // PhoneNumber field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = phonenumber,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            leadingIcon = {
                Text(
                    text = "+31",
                    color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 14.dp, top = 4.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                phonenumber = it
            },
            placeholder = { Text(text = stringResource(id = R.string.phonenumber_text), fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) }
        )
        Spacer(modifier = Modifier.height(29.dp))
        // Password field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = password,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface.copy(Color.White.alpha)),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            visualTransformation = if (passwordVisible) { VisualTransformation.None } else { PasswordVisualTransformation() },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon",
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .size(35.dp))},
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Default.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (passwordVisible) "Hide password" else "show password"
                IconButton(onClick = { passwordVisible =! passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description, modifier = Modifier
                        .size(60.dp)
                        .padding(top = 10.dp, bottom = 10.dp))
                }
            },
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = stringResource(id = R.string.password_text), fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) },
        )
        Spacer(modifier = Modifier.height(33.dp))
        Button(
            onClick = {
                if(phonenumber.isEmpty() || password.isEmpty()) {
                    showEmptyFieldDialog.value = true
                }
                else {
                    loginViewModel.loginUser(context, Login("+31${phonenumber}", password), navController)
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
            modifier = Modifier
                .height(60.dp)
                .width(300.dp)
        ) {
            Text(text = stringResource(id = R.string.login_button_text), color = Color.White, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(33.dp))

        if (isAuthenticating) {
            CircularProgressIndicator(modifier = Modifier.then(Modifier.size(62.dp)), color = Color.White)
        }
    }
}