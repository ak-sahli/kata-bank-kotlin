package io.aksahli.kata.bank

class Transaction(val type: TransactionType, amount: Double) {

    val amount: Double

    init {
        if (amount < 0) { throw IllegalAmountException(requestedAmount = amount) }
        this.amount = amount
    }

    fun amount(): Double = type.value(amount)

}