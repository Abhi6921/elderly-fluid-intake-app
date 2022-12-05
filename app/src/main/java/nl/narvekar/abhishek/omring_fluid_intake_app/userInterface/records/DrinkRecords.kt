package nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.records

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

import androidx.navigation.NavController
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkRecord
import nl.narvekar.abhishek.omring_fluid_intake_app.navigation.AppBottomNav
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.cardCollapsedBackgroundColor
import nl.narvekar.abhishek.omring_fluid_intake_app.ui.theme.cardExpandedBackgroundColor
import nl.narvekar.abhishek.omring_fluid_intake_app.userInterface.dashboard.DashBoardSpinnerAndQuote
import nl.narvekar.abhishek.omring_fluid_intake_app.viewModel.CardListViewModel


@Composable
fun DrinkRecords(navController: NavController, cardListViewModel: CardListViewModel) {

    val cards by cardListViewModel.cards.collectAsState()
    val expandedCardIds by cardListViewModel.expandedCardList.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    elevation = 4.dp,
                    title = {
                        Text("Drink Records")
                    },
                    backgroundColor =  MaterialTheme.colors.primarySurface,
                    navigationIcon = {
                        IconButton(onClick = {/* Do Something*/ }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                )
            }
        },
        content = {
                Row {
                    //DashBoardSpinnerAndQuote(drinkAmount = 0.0f, , null)
                }

                LazyColumn {
                    items(cards, DrinkRecord::dateTime) { card ->
                        ExpandableCard(
                            card = card,
                            onCardArrowClick = { cardListViewModel.onCardArrowClicked(card.dateTime) },
                            expanded = expandedCardIds.contains(card.dateTime)
                        )
                    }
                }
        },
        bottomBar = {
            AppBottomNav(navController = navController)
        }
    )
//        LazyColumn {
//            items(cards, DrinkRecord::dateTime) { card ->
//                ExpandableCard(
//                    card = card,
//                    onCardArrowClick = { cardListViewModel.onCardArrowClicked(card.dateTime) },
//                    expanded = expandedCardIds.contains(card.dateTime)
//                )
//            }
//        }

}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    card: DrinkRecord,
    onCardArrowClick: () -> Unit,
    expanded: Boolean
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(transitionState)
    val cardBgColor by transition.animateColor({
        tween(durationMillis = 450)
    }, label = "bgColorTransition") {
        if (expanded) cardExpandedBackgroundColor else cardCollapsedBackgroundColor
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = 450)
    }, label = "paddingTransition") {
        if (expanded) 48.dp else 24.dp
    }

    val cardElevation by transition.animateDp({
        tween(durationMillis = 450)
    }, label = "elevationTransition") {
        if (expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = 450,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        if (expanded) 0.dp else 16.dp
    }

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = 450)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }

    val context = LocalContext.current
    val contentColour = remember {
        Color(ContextCompat.getColor(context, nl.narvekar.abhishek.omring_fluid_intake_app.R.color.colorDayNightPurple))
    }

    Card(
        backgroundColor = cardBgColor,
        contentColor = contentColour,
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {
        Column {
            Box {
                CardArrow(
                    degrees = arrowRotationDegree,
                    onClick = onCardArrowClick
                )
                CardTitle(title = card.amount)
            }
            ExpandableContent(visible = expanded)
        }
    }
}


@Composable
fun CardArrow(degrees: Float, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Expandable Arrow",
            modifier = Modifier.rotate(degrees),
        )
    }
}

@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun ExpandableContent(
    visible: Boolean = true,
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(450)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(450)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(450)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(450)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.heightIn(100.dp))
            Text(
                text = "Expandable content here",
                textAlign = TextAlign.Center
            )
        }
    }
}
