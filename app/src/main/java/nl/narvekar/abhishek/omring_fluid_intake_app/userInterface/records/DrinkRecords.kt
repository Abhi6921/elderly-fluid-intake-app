package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.narvekar.abhishek.omring_fluid_intake_app.R
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkRecord
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.Routes
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.cardCollapsedBackgroundColor
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.cardExpandedBackgroundColor
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.CardListViewModel


@Composable
fun DrinkRecords(navController: NavController, cardListViewModel: CardListViewModel) {

    val itemIds by cardListViewModel.itemIds.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth().height(60.dp),
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
                                color = Color.White
                            )
                        }
                    }
                }
            }
        },
        content = { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                itemsIndexed(cardListViewModel.items.value) { index, item ->
                    ExpandableContainerView(
                        drinkRecord = item,
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
fun ExpandableView(answerText: String, isExpanded: Boolean) {
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
                .background(Color.DarkGray)
                .padding(15.dp)
        ) {
            Text(
                text = answerText,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ExpandableContainerView(
    drinkRecord: DrinkRecord, 
    onClickItem: () -> Unit,
    expanded: Boolean
) {
    Box(modifier = Modifier.background(Color.Green)) {
        Column {
            HeaderView(questionText = drinkRecord.question, onClickItem = onClickItem)
            ExpandableView(answerText = drinkRecord.answer , isExpanded = expanded)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExpandableContainerViewPreview() {
    ExpandableContainerView(
        drinkRecord = DrinkRecord("Question", "Answer"),
        onClickItem = {},
        expanded = true
    )
}



