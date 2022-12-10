package nl.narvekar.abhishek.omring_fluid_intake_app.data

import java.util.Objects

data class DrinkDate (
    val dateTime: String,
    val drinkRecord: DrinkRecord
)

data class DrinkRecord(
    val time: String,
    val drinkAmount: String
)

val AllDrinkDates = arrayListOf(
    DrinkDate("13/03/2022", DrinkRecord("09:15", "100ml")),
    DrinkDate("13/03/2022", DrinkRecord("09:15", "100ml")),
    DrinkDate("13/03/2022", DrinkRecord("10:15", "150ml")),
    DrinkDate("13/03/2022", DrinkRecord("11:15", "200ml")),
    DrinkDate("13/03/2022", DrinkRecord("12:15", "100ml")),
    DrinkDate("13/03/2022", DrinkRecord("13:15", "150ml"))
)


