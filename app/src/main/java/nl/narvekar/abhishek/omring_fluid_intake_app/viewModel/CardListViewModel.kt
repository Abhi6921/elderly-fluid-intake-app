package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi

import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkDate
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkLogResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.Recipe
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

private const val PAGE_SIZE = 30
class CardListViewModel : ViewModel() {


    val drinkRecords = Pager(PagingConfig(pageSize = 20)) {
        DrinkLogsPager()
    }.flow.cachedIn(viewModelScope)


    private val itemIdsList = MutableStateFlow(listOf<Int>())
    val itemIds: StateFlow<List<Int>> get() = itemIdsList

    //var drinkDateResponse: List<DrinkLogResponse> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")


    private val mutableDrinkLogsListResponse = MutableStateFlow<List<DrinkLogResponse>?>(null)
    var drinkLogsListState: StateFlow<List<DrinkLogResponse>?> = mutableDrinkLogsListResponse

    val page = mutableStateOf(1)
    private var drinkLogScrollPosition = 0

    init {
        getAllDrinkDates()
    }

     fun getAllDrinkDates() {
        viewModelScope.launch(Dispatchers.IO) {
            val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
            val adminToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQURNSU4iLCJDQVJFX0dJVkVSIl0sImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiIrMzE2NDU4MjYwMDAiLCJuYmYiOjE2NzE2NTgyNTEsImV4cCI6MTcwMzE5NDI1MSwiaWF0IjoxNjcxNjU4MjUxLCJpc3MiOiJEcmlua0FwcFJlY2lwZXMuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJEcmlua0FwcFVzZXJzIC8gUGF0aWVudHMgLyBDYXJlZ2l2ZXJzIC8gQWRtaW5zIn0.sgh_qAXL9GyQ_GLiXjPOBxZBQlaSaC91Cxc8iobF9XM"
            val patientId = AppSession.getPatientId()
            try {
                val drinkLogs = usersAuthApi.getPatientDrinkLogs("Bearer ${adminToken}", patientId, "01/12/2022", "09/12/2023",1, 20)

                if (drinkLogs.isSuccessful) {
                    mutableDrinkLogsListResponse.emit(drinkLogs.body()!!)
                    Log.d("Success!", "Drink logs are NOT empty")
                }
                else {
                    Log.d("Failure!", "Drink logs are empty")
                }
            }
            catch (ex: Exception) {
                errorMessage = ex.message.toString()
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