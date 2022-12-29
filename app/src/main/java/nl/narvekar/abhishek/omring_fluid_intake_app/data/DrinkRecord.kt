package nl.narvekar.abhishek.omring_fluid_intake_app.data

import java.time.temporal.TemporalAmount
import java.util.Objects

//data class DrinkDate (
//    val dateTime: String,
//    var drinkRecord: List<DrinkRecord>
//)

//data class DrinkRecord(
//    val time: String,
//    val drinkAmount: String
//)

data class DrinkLogResponse(
    val drinkId: String? = null,
    val dateTime: String? = null,
    val amount: Int? = null,
    val patientId: String? = null
)
data class DrinkDate (
    val dateTime: String? = null,
    var drinkRecord: List<DrinkRecord>? = null
)

data class DrinkRecord(
    val dateTime: String? = null,
    val amount: Int? = null
)

//val AllDrinkDates = arrayListOf(
//    DrinkDate(
//        "13/03/2022",
//        listOf(
//            DrinkRecord("07:12", "100ml"),
//            DrinkRecord("08:12", "150ml"),
//            DrinkRecord("09:12", "200ml"),
//            DrinkRecord("10:12", "150ml"),
//            DrinkRecord("11:12", "200ml"),
//            DrinkRecord("12:12", "100ml")
//        )
//    ),
//    DrinkDate("14/03/2022",
//        listOf(
//            DrinkRecord("07:15", "100ml"),
//            DrinkRecord("08:15", "150ml"),
//            DrinkRecord("09:15", "200ml"),
//            DrinkRecord("10:15", "150ml"),
//            DrinkRecord("11:15", "200ml"),
//            DrinkRecord("12:15", "100ml")
//        )
//    ),
//    DrinkDate("15/03/2022",
//        listOf(
//            DrinkRecord("07:20", "100ml"),
//            DrinkRecord("08:20", "150ml"),
//            DrinkRecord("09:20", "200ml"),
//            DrinkRecord("10:20", "150ml"),
//            DrinkRecord("11:20", "200ml"),
//            DrinkRecord("12:20", "100ml")
//        )
//    ),
//    DrinkDate("14/03/2022",
//        listOf(
//            DrinkRecord("07:25", "100ml"),
//            DrinkRecord("08:25", "150ml"),
//            DrinkRecord("09:25", "200ml"),
//            DrinkRecord("10:25", "150ml"),
//            DrinkRecord("11:25", "200ml"),
//            DrinkRecord("12:25", "100ml")
//        )
//    ),
//    DrinkDate("15/03/2022",
//        listOf(
//            DrinkRecord("07:20", "100ml"),
//            DrinkRecord("08:20", "150ml"),
//            DrinkRecord("09:20", "200ml"),
//            DrinkRecord("10:20", "150ml"),
//            DrinkRecord("11:20", "200ml"),
//            DrinkRecord("12:20", "100ml")
//        )
//    ),
//
//
//    DrinkDate("15/03/2022",
//        listOf(
//            DrinkRecord("07:20", "100ml"),
//            DrinkRecord("08:20", "150ml"),
//            DrinkRecord("09:20", "200ml"),
//            DrinkRecord("10:20", "150ml"),
//            DrinkRecord("11:20", "200ml"),
//            DrinkRecord("12:20", "100ml")
//        )
//    ),
//    DrinkDate("15/03/2022",
//        listOf(
//            DrinkRecord("07:20", "100ml"),
//            DrinkRecord("08:20", "150ml"),
//            DrinkRecord("09:20", "200ml"),
//            DrinkRecord("10:20", "150ml"),
//            DrinkRecord("11:20", "200ml"),
//            DrinkRecord("12:20", "100ml")
//        )
//    ),
//    DrinkDate("15/03/2022",
//        listOf(
//            DrinkRecord("07:20", "100ml"),
//            DrinkRecord("08:20", "150ml"),
//            DrinkRecord("09:20", "200ml"),
//            DrinkRecord("10:20", "150ml"),
//            DrinkRecord("11:20", "200ml"),
//            DrinkRecord("12:20", "100ml")
//        )
//    ),
////    DrinkDate("14/03/2022", DrinkRecord("09:15", "100ml")),
////    DrinkDate("15/03/2022", DrinkRecord("10:15", "150ml")),
////    DrinkDate("16/03/2022", DrinkRecord("11:15", "200ml")),
////    DrinkDate("17/03/2022", DrinkRecord("12:15", "100ml")),
////    DrinkDate("18/03/2022", DrinkRecord("13:15", "150ml"))
//)
//
//val ALLREACORDSLIST = arrayListOf(
//    DrinkRecord("09:15", "100ml"),
//    DrinkRecord("09:15", "100ml"),
//    DrinkRecord("09:15", "100ml"),
//    DrinkRecord("09:15", "100ml"),
//    DrinkRecord("09:15", "100ml"),
//    DrinkRecord("09:15", "100ml"),
//    DrinkRecord("09:15", "100ml"),
//)


