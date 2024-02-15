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
