package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi


import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkLogResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*


class CardListViewModel : ViewModel() {

    val drinkrecords = Pager(PagingConfig(pageSize = 1, enablePlaceholders = false)) {
        DrinkRecordsPager()
    }.flow.cachedIn(viewModelScope)

    fun formatDateTimeForDrinkLogs(dateTime: String): String {
        // remove unnecessary values after seconds section in datetime
        val strippedDate = dateTime.dropLast(13)
        val date = LocalDateTime.parse(strippedDate)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a", Locale.getDefault())
        val zonedDateTime = formatter.format(date)
        return zonedDateTime
    }
}