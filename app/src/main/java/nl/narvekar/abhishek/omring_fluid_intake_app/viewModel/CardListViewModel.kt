package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CardListViewModel : ViewModel() {

    val drinkrecords = Pager(PagingConfig(pageSize = 1, enablePlaceholders = false)) {
        DrinkRecordsPager()
    }.flow.cachedIn(viewModelScope)

    fun formatDateTimeForDrinkLogs(dateTime: String): String {
        // removes unnecessary values after seconds section in datetime, since the api returns the date in following format: 2023-01-16T18:23:00.0790479+00:00
        val strippedDate = dateTime.dropLast(13)
        val date = LocalDateTime.parse(strippedDate)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a", Locale.getDefault())
        val zonedDateTime = formatter.format(date)
        return zonedDateTime
    }
}