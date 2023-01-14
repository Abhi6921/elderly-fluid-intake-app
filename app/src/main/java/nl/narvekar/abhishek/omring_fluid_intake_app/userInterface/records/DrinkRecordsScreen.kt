package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records

import android.os.Build
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

//@Preview(showBackground = true, widthDp = 900, heightDp = 1280)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DrinkRecords(
    navController: NavController,
    cardListViewModel: CardListViewModel = viewModel(),
    patientViewModel: PatientViewModel = viewModel()
) {

    val patientId = AppSession.getPatientId()
    val dailyLimit = AppSession.getDailyLimit()

    val currentFluidIntakeStatus = patientViewModel.getCurrentFluidIntakeStatus(patientId)

    val drinksLogs by cardListViewModel.drinkLogsListState.collectAsState()
    val itemIds by cardListViewModel.itemIds.collectAsState()

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
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (currentFluidIntakeStatus.Achieved?.toFloat() == null) {
                    Text(
                        text = stringResource(id = R.string.zero_target_achieved),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 280.dp, top = 38.dp, bottom = 20.dp, end = 30.dp)
                    )
                    DashBoardSpinnerAndQuote(drinkAmount = 0.0f, dailyLimit = 100)
                }
                else {

                    Text(
                        text = "Target achieved: ${currentTargetAchieved?.toInt()}%",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 280.dp, top = 38.dp, bottom = 20.dp, end = 30.dp)
                    )
                    DashBoardSpinnerAndQuote(drinkAmount = currentFluidIntakeStatus.Achieved.toFloat(), dailyLimit = dailyLimit)
                }
            }
            LazyColumn(Modifier.padding(start = 0.dp, top = 420.dp, end = 0.dp)) {
                drinksLogs?.let { allDrinkLogs ->
                    itemsIndexed(allDrinkLogs) { index, drinkRecord ->
                        ExpandableContainerView(
                            drinklogResponse = drinkRecord,
                            onClickItem = { cardListViewModel.onItemClicked(index) },
                            expanded = itemIds.contains(index)
                        )
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
fun HeaderView(datetime: String, drinkAmount: String, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .background(recordsTitleColor)
            .height(100.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color.Blue))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClickItem
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(modifier = Modifier
                .size(390.dp)

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
fun ExpandableView(time: String, drinkAmount: String, isExpanded: Boolean) {
    // Enter transition
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    // Closing Animation
    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300),
        ) + fadeOut(animationSpec = tween(300))
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Box(
            modifier = Modifier
                .background(recordsExpandListColor)
                .border(BorderStroke(2.dp, Color.Black))
                .padding(15.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.cup_image),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = time,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = drinkAmount,
                    fontSize = 25.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = omringButtonColor),
                    onClick = {
                        // edit drink on this action
                    }
                ) {
                    Text(text = "Edit", color = Color.White)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = omringButtonColor),
                    onClick = {
                        // remove record on this action
                    }) {
                    Text(text = "Remove", color = Color.White)
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpandableContainerView(
    drinklogResponse: DrinkLogResponse,
    onClickItem: () -> Unit,
    expanded: Boolean
) {
    Box(modifier = Modifier.background(Color.Green)) {
        Column {
            val drinkDateTime = formatDateTimeForDrinkLogs(drinklogResponse.dateTime.toString())
            val time = formatTimeForDrinkLogs(drinklogResponse.dateTime.toString())
            HeaderView(datetime = drinkDateTime, drinklogResponse.amount.toString(),onClickItem = onClickItem)
            ExpandableView(time = time, drinkAmount = drinklogResponse.amount.toString(), isExpanded = expanded)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTimeForDrinkLogs(dateTime: String): String {
    val strippedDate = dateTime.dropLast(13)
    val date = LocalDateTime.parse(strippedDate)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.GERMANY)
    val zonedDateTime = formatter.format(date)
    return zonedDateTime
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeForDrinkLogs(dateTime: String) : String {
    val strippedDate = dateTime.dropLast(13)
    val date = LocalDateTime.parse(strippedDate)
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.GERMANY)
    val zonedTime = formatter.format(date)
    return zonedTime

}