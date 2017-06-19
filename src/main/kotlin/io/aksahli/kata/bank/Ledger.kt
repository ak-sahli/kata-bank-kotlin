package io.aksahli.kata.bank

import io.aksahli.kata.bank.TransactionType.*

class Ledger {

    private val transactions: MutableList<Transaction> = mutableListOf()

    fun record(transaction: Transaction) = transactions.add(transaction)

    fun balance() = transactions
            .map { transaction -> transaction.amount() }
            .reduce { accumulatedAmount, amount -> amount + accumulatedAmount }

    fun transactions() = transactions.map { it }

    fun transactions(transactionType: TransactionType, account: String) = transactions()
            .filter { when(transactionType) {
                DEPOSIT  -> it.from == account
                WITHDRAW -> it.to == account
            }}

}
