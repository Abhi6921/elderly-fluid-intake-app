package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkRecord

class CardListViewModel : ViewModel() {

    // holds the list of cards
    private val _cards = MutableStateFlow(listOf<DrinkRecord>())
    val cards: StateFlow<List<DrinkRecord>> get() = _cards

    // holds a list of expanded card ids changes state onclicked
    private val _expandCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardList: StateFlow<List<Int>> get() = _expandCardIdsList

    init {
        getFakeData()
    }

    // actual processing of the data will happen here
    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<DrinkRecord>()
                repeat(20) {
                    testList += DrinkRecord(dateTime = it, amount = "Drink $it")
                    _cards.emit(testList)
                }
            }
        }
    }

    fun onCardArrowClicked(cardId: Int) {
        _expandCardIdsList.value = _expandCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) {
                list.remove(cardId)
            }
            else {
                list.add(cardId)
            }
        }
    }

}