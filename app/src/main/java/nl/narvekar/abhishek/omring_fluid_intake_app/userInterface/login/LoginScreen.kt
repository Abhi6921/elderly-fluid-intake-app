package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
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
import androidx.navigation.navArgument
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Login
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.LoginViewModel


@Composable
fun LoginUI(loginViewModel: LoginViewModel, navController: NavController) {

    val context = LocalContext.current
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
            text = "Welcome Back!",
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
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = "personIcon",
//                    Modifier
//                        .width(60.dp)
//                        .height(40.dp)
//                )
//                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "+31",
                    color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 14.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                phonenumber = it
            },
            label = { Text(text = "PhoneNumber", fontSize = 20.sp) }
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
                Modifier
                    .width(60.dp)
                    .height(40.dp))},
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Default.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (passwordVisible) "Hide password" else "show password"
                IconButton(onClick = { passwordVisible =! passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center) },
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                // todo 0. create new key in shared preferences by the name of drink amount
                loginViewModel.loginUser(context, Login("+31${phonenumber}", password), navController)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7D71)),
            modifier = Modifier
                .height(60.dp)
                .width(300.dp)
        ) {
            Text(text = "Login here", color = Color.White, fontSize = 30.sp)
        }
    }
}