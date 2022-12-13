package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records


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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkDate
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.omringButtonColor
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.recordsExpandListColor
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.recordsTitleColor
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardSpinnerAndQuote
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.components.CircularProgressBar
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.CardListViewModel


@Composable
fun DrinkRecords(navController: NavController, cardListViewModel: CardListViewModel) {
    val itemIds by cardListViewModel.itemIds.collectAsState()
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
                                text = "Drink Records",
                                color = Color.White,
                                fontSize = 34.sp
                            )
                        }
                    }
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressBar(percentage = 0.0f, number = 100)
                    Spacer(modifier = Modifier.width(40.dp))
                    Image(
                        painter = painterResource(R.drawable.rain_drop),
                        contentDescription = "drop emoji",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(160.dp)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    Box(modifier = Modifier
                        .size(250.dp)
                        .padding(0.dp)
                    ) {
                        val image = painterResource(id = R.drawable.message_box)
                        Image(painter = image, contentDescription = null)
                        Text(
                            text = "A cup a day keeps the doctor away",
                            textAlign = TextAlign.Center, fontSize = 29.sp,
                            color = Color.White
                        )
                    }
                }
                Column {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Drink Fluid")
                    }
                }

                LazyColumn(modifier = Modifier.padding(padding)) {
                    itemsIndexed(cardListViewModel.items1.value) { index, item ->
                        ExpandableContainerView(
                            drinkDate = item,
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
fun HeaderView(questionText: String, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .background(recordsTitleColor)
            .height(80.dp)
            .fillMaxWidth()
            .border(BorderStroke(5.dp, Color.Blue))
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
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .size(200.dp)

            ) {
                Text(
                    text = questionText,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "3.0 Litres",
                fontSize = 21.sp,
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
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = drinkAmount,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
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

@Composable
fun ExpandableContainerView(
    drinkDate: DrinkDate,
    onClickItem: () -> Unit,
    expanded: Boolean
) {
    Box(modifier = Modifier.background(Color.Green)) {
        Column {
            HeaderView(questionText = drinkDate.dateTime, onClickItem = onClickItem)
            drinkDate.drinkRecord.forEach{ drinkRecord ->
                ExpandableView(time = drinkRecord.time, drinkAmount = drinkRecord.drinkAmount, isExpanded = expanded)
            }

        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ExpandableContainerViewPreview() {
//    ExpandableContainerView(
//        drinkRecord = DrinkRecord("Question", "Answer"),
//        onClickItem = {},
//        expanded = true
//    )
//}



