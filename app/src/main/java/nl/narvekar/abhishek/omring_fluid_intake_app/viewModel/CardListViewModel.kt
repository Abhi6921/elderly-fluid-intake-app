package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.provider.ContactsContract
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.AllDrinkDates
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkDate
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkRecord
import nl.narvekar.abhishek.omring_fluid_intake_app.data.PatientResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

class CardListViewModel : ViewModel() {

    private val itemIdsList = MutableStateFlow(listOf<Int>())
    val itemIds: StateFlow<List<Int>> get() = itemIdsList

    private val dateItemList = MutableStateFlow(listOf<DrinkDate>())
    val items1: StateFlow<List<DrinkDate>> get() = dateItemList


    // call getData() method in constructors so whenever screen appears we load the data
    init {
        getAllDrinkDates()
        //getAllDrinkRecords()
    }

//     getting all the demo data change with value from api
//    private fun getAllDrinkRecords() {
//        viewModelScope.launch {
//            withContext(Dispatchers.Default) {
//                itemsList.emit(AllDrinkRecords)
//            }
//        }
//    }

    private fun getAllDrinkDates() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                dateItemList.emit(AllDrinkDates)
            }
        }
    }



    fun onItemClicked(itemId: Int) {
        itemIdsList.value = itemIdsList.value.toMutableList().also { list ->
            if (list.contains(itemId)) {
                list.remove(itemId)
            } else {
                list.add(itemId)
            }
        }
    }
}