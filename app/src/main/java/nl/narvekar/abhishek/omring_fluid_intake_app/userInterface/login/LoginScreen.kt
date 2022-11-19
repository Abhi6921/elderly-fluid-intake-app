package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.narvekar.abhishek.omring_fluid_intake_app.R

@Preview(showBackground = true, widthDp = 1180, heightDp = 1200)
@Composable
fun LoginUI() {
    // some changes
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

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Username field
        OutlinedTextField(
            modifier = Modifier
                .height(81.dp)
                .width(400.dp),
            value = username,
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
            label = { Text(text = "Username", fontSize = 20.sp) }
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
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon",
                Modifier
                    .width(60.dp)
                    .height(40.dp))},
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center) },
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                /*Navigate to Home screen */
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