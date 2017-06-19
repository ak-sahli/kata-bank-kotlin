package io.aksahli.kata.bank

abstract class Transaction(val type: TransactionType, amount: Double,val from: String = "",val to: String = "") {

    val amount: Double

    init {
        if (amount < 0) { throw IllegalAmountException(requestedAmount = amount) }
        this.amount = amount
    }

   abstract fun amount(): Double

}