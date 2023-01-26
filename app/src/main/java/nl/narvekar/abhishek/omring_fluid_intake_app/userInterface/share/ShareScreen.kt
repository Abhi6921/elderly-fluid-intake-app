package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.share

import android.content.Intent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.FluidTopAppBar

@Composable
fun ShareScreen(navController: NavController) {
    Scaffold(
        topBar = {
            FluidTopAppBar(stringResource(id = R.string.share_top_bar_title))
        },
        content = {
            val context = LocalContext.current
            val shareApp = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, stringResource(id = R.string.share_text))
                putExtra(Intent.EXTRA_SUBJECT, stringResource(id = R.string.drink_subject_text))
                putExtra(Intent.EXTRA_TITLE, stringResource(id = R.string.sharing_title))
                type = "text/plain"
            }, null)
            startActivity(context, Intent.createChooser(shareApp, null), null)
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )

}