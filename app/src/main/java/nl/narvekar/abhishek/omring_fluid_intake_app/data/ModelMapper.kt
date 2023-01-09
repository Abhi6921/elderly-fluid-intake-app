package nl.narvekar.abhishek.omring_fluid_intake_app.data

class ModelMapper {

    fun mapDrinkRecords(records: List<DrinkLogResponse>) : Result<List<DrinkLogResponse>> = runCatching {
        records.map {
            with(it) {
                DrinkLogResponse(
                    drinkId = it.drinkId,
                    dateTime = it.dateTime,
                    amount = it.amount,
                    patientId = it.patientId
                )
            }
        }
    }
}