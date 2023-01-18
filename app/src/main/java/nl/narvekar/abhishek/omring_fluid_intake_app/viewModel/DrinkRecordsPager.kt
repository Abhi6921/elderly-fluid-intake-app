package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkLogResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.LogDrinkResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

const val NETWORK_PAGE_SIZE = 1
private val INITIAL_LOAD_SIZE = 1
class DrinkRecordsPager() : PagingSource<Int, DrinkLogResponse>() {

    val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()
    val mapper = DrinkRecordsMapper()

    override val keyReuseSupported: Boolean = true
    override fun getRefreshKey(state: PagingState<Int, DrinkLogResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrinkLogResponse> {

        // start refresh at position 1 if undefined
        val position = params.key ?: 0
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        val adminToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQURNSU4iLCJDQVJFX0dJVkVSIl0sImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiIrMzE2NDU4MjYwMDAiLCJuYmYiOjE2NzE2NTgyNTEsImV4cCI6MTcwMzE5NDI1MSwiaWF0IjoxNjcxNjU4MjUxLCJpc3MiOiJEcmlua0FwcFJlY2lwZXMuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJEcmlua0FwcFVzZXJzIC8gUGF0aWVudHMgLyBDYXJlZ2l2ZXJzIC8gQWRtaW5zIn0.sgh_qAXL9GyQ_GLiXjPOBxZBQlaSaC91Cxc8iobF9XM"
        val patientId = AppSession.getPatientId()
        return try {
            val jsonResponse = usersAuthApi.getPatientDrinkLogs(
                "Bearer ${adminToken}",
                patientId,
                "18/01/2023",
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