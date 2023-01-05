package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.share

import android.content.Intent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.FluidTopAppBar

@Composable
fun ShareScreen(navController: NavController) {
    Scaffold(
        topBar = {
            FluidTopAppBar("Share")
        },
        content = {
            val context = LocalContext.current
            val shareApp = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hey, Download this drink app of Omring and stay hydrated!")
                putExtra(Intent.EXTRA_SUBJECT, "Drink App")
                putExtra(Intent.EXTRA_TITLE, "Share this app with friends and family!")
                type = "message/rfc822"
            }, null)
            startActivity(context, Intent.createChooser(shareApp, null), null)
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )

}