package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkDate
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
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
            LazyColumn(modifier = Modifier.padding(padding)) {
                itemsIndexed(cardListViewModel.items1.value) { index, item ->
                    ExpandableContainerView(
                        drinkDate = item,
                        onClickItem = { cardListViewModel.onItemClicked(index) },
                        expanded = itemIds.contains(index)
                    )
                }
            }
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )
}

// view to expand and collapse the expandable
@Composable
fun HeaderView(questionText: String, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClickItem
            )
            .padding(8.dp)
    ) {
        Text(
            text = questionText,
            fontSize = 17.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
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
                .background(Color.White)
                .padding(15.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.cup_image),
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
                    onClick = {
                        // edit drink on this action
                    }
                ) {
                    Text(text = "Edit")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(modifier = Modifier.weight(0.5f), onClick = {
                    // remove button on this action
                }) {
                    Text(text = "Remove")
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
            ExpandableView(time = drinkDate.drinkRecord.time, drinkAmount = drinkDate.drinkRecord.drinkAmount, isExpanded = expanded)
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



