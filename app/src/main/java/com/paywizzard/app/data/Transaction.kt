package com.paywizzard.app.data

enum class TransactionType{
    AIRTIME,
    DATA,
    CABLE_SUB,
    TRANSFER,
    ELECTRICITY_BILL,
    EDUCATIONAL_PAYMENT,
    WALLET_TOP_UP
}
data class Transaction(
    val title: String,
    val amount: Double,
    val dateAndTime: String,
    val type:TransactionType,
    val tRef: String
)

fun transactionList(): List<Transaction> {
    val transaction1 = Transaction(
        title = "Airtime",
        amount = 298.09,
        dateAndTime = "2024-02-13 12:30 PM",
        type = TransactionType.AIRTIME,
        tRef = "TRX123456"
    )

    val transaction2 = Transaction(
        title = "Data Purchase",
        amount = 305.58,
        dateAndTime = "2024-02-13 3:45 PM",
        type = TransactionType.DATA,
        tRef = "TRX789012"
    )

    val transaction3 = Transaction(
        title = "Bills",
        amount = 200.00,
        dateAndTime = "2024-02-13 12:30 PM",
        type = TransactionType.AIRTIME,
        tRef = "TRX123456"
    )

    val transaction4 = Transaction(
        title = "Data Purchase",
        amount = 305.58,
        dateAndTime = "2024-02-13 3:45 PM",
        type = TransactionType.DATA,
        tRef = "TRX789012"
    )
    val transaction5 = Transaction(
        title = "Airtime",
        amount = 207.80,
        dateAndTime = "2024-02-13 12:30 PM",
        type = TransactionType.AIRTIME,
        tRef = "TRX123456"
    )

    val transaction6 = Transaction(
        title = " Data Purchase",
        amount = 3575.5,
        dateAndTime = "2024-02-13 3:45 PM",
        type = TransactionType.DATA,
        tRef = "TRX789012"
    )

    val transaction7 = Transaction(
        title = "Fund wallet",
        amount = 3575.50,
        dateAndTime = "2024-02-13 3:45 PM",
        type = TransactionType.WALLET_TOP_UP,
        tRef = "TRX789012"
    )

    // Putting transactions in a list

    //emptyList<Transaction>()
    return listOf(
        transaction1,
        transaction2,
        transaction3,
        transaction4,
        transaction5,
        transaction6,
        transaction7
    )
}