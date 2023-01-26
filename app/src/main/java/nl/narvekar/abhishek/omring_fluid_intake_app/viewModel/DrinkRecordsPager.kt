package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkLogResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

const val NETWORK_PAGE_SIZE = 1
private val INITIAL_LOAD_SIZE = 0
class DrinkRecordsPager() : PagingSource<Int, DrinkLogResponse>() {

    val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
    val mapper = DrinkRecordsMapper()

    override val keyReuseSupported: Boolean = true
    override fun getRefreshKey(state: PagingState<Int, DrinkLogResponse>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrinkLogResponse> {

        // start refresh at position 1 if undefined
        val position = (params.key ?: INITIAL_LOAD_SIZE)
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

        // this endpoint works only on admin token, so I had to hardcode the admin token
        // there was no endpoint in the api for retrieving the drink logs, for that particular day for the patient using their token
        // and since the admin endpoint works a bit differently so had to hardcode some values here
        val adminToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQURNSU4iLCJDQVJFX0dJVkVSIl0sImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiIrMzE2NDU4MjYwMDAiLCJuYmYiOjE2NzQ0OTk4MDYsImV4cCI6MTcwNjAzNTgwNiwiaWF0IjoxNjc0NDk5ODA2LCJpc3MiOiJEcmlua0FwcFJlY2lwZXMuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJEcmlua0FwcFVzZXJzIC8gUGF0aWVudHMgLyBDYXJlZ2l2ZXJzIC8gQWRtaW5zIn0.hSt385jrkqYfBirmZQaToX5DbRx0h5m0SJuzqBG4ZVk"
        val patientId = AppSession.getPatientId()
        return try {
            val jsonResponse = usersAuthApi.getPatientDrinkLogs(
                "Bearer ${adminToken}",
                patientId,
                "22/01/2023",
                "28/01/2025",
                offset,
                limit = params.loadSize
            )
            val response = mapper.mapDrinkRecords(jsonResponse.body()!!)
            val nextKey = if(response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

}


class DrinkRecordsMapper() {
    fun mapDrinkRecords(records: List<DrinkLogResponse>) : List<DrinkLogResponse> {
        with(records) {
            return if (this.isNotEmpty() == true) {
                this.map { DrinkLogResponse(drinkId = it.drinkId, dateTime = it.dateTime, amount = it.amount, patientId = it.patientId) }
            } else {
                emptyList()
            }
        }
    }
}