package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records

import android.os.Build
import android.os.Build.VERSION.SDK
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkLogResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.omringButtonColor
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.recordsExpandListColor
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.recordsTitleColor
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardSpinnerAndQuote
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.CardListViewModel
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.PatientViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt



@Composable
fun DrinkRecords(
    navController: NavController,
    patientViewModel: PatientViewModel = viewModel(),
    cardListViewModel: CardListViewModel = viewModel(),
) {

    val patientId = AppSession.getPatientId()
    val dailyLimit = AppSession.getDailyLimit()

    val currentFluidIntakeStatus = patientViewModel.getCurrentFluidIntakeStatus(patientId)

    val drinksLogs = cardListViewModel.drinkrecords.collectAsLazyPagingItems()
    val currentTargetAchieved = ((currentFluidIntakeStatus.Achieved?.toFloat()?.div(dailyLimit))?.times(100))?.roundToInt()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                backgroundColor = Color(0xFF1BAEEE),
                elevation = 0.dp,

                ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high
                        ) {

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                text = stringResource(id = R.string.drink_records_title),
                                color = Color.White,
                                fontSize = 34.sp
                            )
                        }
                    }
                }
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.fillMaxWidth()) {
                if (currentFluidIntakeStatus.Achieved?.toFloat() == null) {
                    Text(
                        text = stringResource(id = R.string.zero_target_achieved),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 280.dp, top = 8.dp, bottom = 20.dp, end = 30.dp)
                    )
                    DashBoardSpinnerAndQuote(drinkAmount = 0.0f, dailyLimit = 100)
                }
                else {

                    Text(
                        text = "Target achieved: ${currentTargetAchieved?.toInt()}%",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 300.dp, top = 8.dp, bottom = 20.dp, end = 30.dp)
                    )
                    DashBoardSpinnerAndQuote(drinkAmount = currentFluidIntakeStatus.Achieved.toFloat(), dailyLimit = dailyLimit)
                }
            }
            if(drinksLogs.itemCount == 0) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.no_drink_records), fontSize = 24.sp)
                }
            }
            else {
                LazyColumn(
                    Modifier
                        .padding(start = 0.dp, top = 320.dp, end = 0.dp)
                        .padding(innerPadding)
                ) {
                    itemsIndexed(drinksLogs) { index, drinkRecord ->
                        if (drinkRecord != null) {
                            ContainerView(
                                drinklogResponse = drinkRecord,
                                cardListViewModel = cardListViewModel
                            )
                        }
                    }
                }
            }
            drinksLogs.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Blue)
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )
}


@Composable
fun DrinkRecordsView(datetime: String, drinkAmount: String) {
    Box(
        modifier = Modifier
            .background(recordsTitleColor)
            .height(100.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color.Blue))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(modifier = Modifier
                .size(490.dp)

            ) {
                Text(
                    text = "Datetime: $datetime",
                    fontSize = 28.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(65.dp))
            Text(
                text = "Intake: ${drinkAmount} ml",
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ContainerView(
    drinklogResponse: DrinkLogResponse,
    cardListViewModel: CardListViewModel
) {
    Box(modifier = Modifier.background(Color.Green)) {
        Column {
            val drinkDateTime =  cardListViewModel.formatDateTimeForDrinkLogs(drinklogResponse.dateTime.toString())

            DrinkRecordsView(datetime = drinkDateTime, drinklogResponse.amount.toString())
        }
    }
}
