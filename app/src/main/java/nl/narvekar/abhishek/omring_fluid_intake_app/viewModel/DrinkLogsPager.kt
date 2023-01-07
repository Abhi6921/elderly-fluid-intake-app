package nl.narvekar.abhishek.omring_fluid_intake_app.viewModel

import android.view.Display.Mode
import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.narvekar.abhishek.omring_fluid_intake_app.api.UsersAuthApi
import nl.narvekar.abhishek.omring_fluid_intake_app.data.DrinkLogResponse
import nl.narvekar.abhishek.omring_fluid_intake_app.data.ModelMapper
import nl.narvekar.abhishek.omring_fluid_intake_app.utils.AppSession

class DrinkLogsPager : PagingSource<Int, DrinkLogResponse>() {
    private val modelMapper = ModelMapper()

    val usersAuthApi = UsersAuthApi.getUsersAuthApiInstance()

    override val keyReuseSupported: Boolean = true
    override fun getRefreshKey(state: PagingState<Int, DrinkLogResponse>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrinkLogResponse> {
        val page = params.key ?: 0
        val size = params.loadSize
        val from = page * size
        val result = fetch(from, size).getOrElse {
            return LoadResult.Error(it)
        }
        return LoadResult.Page(data = result, prevKey = page, nextKey = if(result.isEmpty()) null else page + 1)
    }

    private suspend fun fetch(startkey: Int, loadSize: Int) : Result<List<DrinkLogResponse>> {

        val adminToken = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQURNSU4iLCJDQVJFX0dJVkVSIl0sImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiIrMzE2NDU4MjYwMDAiLCJuYmYiOjE2NzE2NTgyNTEsImV4cCI6MTcwMzE5NDI1MSwiaWF0IjoxNjcxNjU4MjUxLCJpc3MiOiJEcmlua0FwcFJlY2lwZXMuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJEcmlua0FwcFVzZXJzIC8gUGF0aWVudHMgLyBDYXJlZ2l2ZXJzIC8gQWRtaW5zIn0.sgh_qAXL9GyQ_GLiXjPOBxZBQlaSaC91Cxc8iobF9XM"
        val patientId = AppSession.getPatientId()
        val response = usersAuthApi.getPatientDrinkLogs("Bearer ${adminToken}", patientId, "01/12/2022", "09/12/2023",startkey, loadSize)

        return when {
            response.isSuccessful -> {
                val body = response.body()
                if (body !==  null) {
                    modelMapper.mapDrinkRecords(body)
                } else {
                    Result.failure(IllegalStateException("body was empty"))
                }
            }
            else -> Result.failure(IllegalStateException("something went wrong"))
        }
    }



}