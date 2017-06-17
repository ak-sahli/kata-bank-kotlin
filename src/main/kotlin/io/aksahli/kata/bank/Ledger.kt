package io.aksahli.kata.bank

class Ledger {

    private val transactions: MutableList<Transaction> = mutableListOf()

    fun record(transaction: Transaction) = transactions.add(transaction)

    fun balance() = transactions
            .map { transaction -> transaction.amount() }
            .reduce { accumulatedAmount, amount -> amount + accumulatedAmount }

    fun transactions() = transactions.map { it }

}
