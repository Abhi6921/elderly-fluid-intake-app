package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Allitems
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkRecord

class CardListViewModel : ViewModel() {

    private val itemsList = MutableStateFlow(listOf<DrinkRecord>())
    val items: StateFlow<List<DrinkRecord>> get() = itemsList

    private val itemIdsList = MutableStateFlow(listOf<Int>())
    val itemIds: StateFlow<List<Int>> get() = itemIdsList


    // call getData() method in constructors so whenever screen appears we load the data
    init {
        getData()
    }

    // getting all the demo data change with value from api
    private fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                itemsList.emit(Allitems)
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