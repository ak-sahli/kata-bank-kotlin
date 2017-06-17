package io.aksahli.kata.bank

import io.aksahli.kata.bank.TransactionType.*

class Account(val owner: String, initialAmount: Double = 0.0) {

    val ledger = Ledger()

    init { deposit(initialAmount) }

    fun deposit(amount: Double) {
        ledger.record(Transaction(DEPOSIT, amount))
    }

    fun withdraw(requestedAmount: Double) {
        if (requestedAmount > this.balance()) {
            throw InsufficientBalanceException(requestedAmount, this.balance())
        }
        ledger.record(Transaction(WITHDRAW, requestedAmount))
    }

    fun transfer(amount: Double, payeeAccount: Account) {
        withdraw(amount)
        payeeAccount.deposit(amount)
    }

    fun balance() = ledger.balance()

    fun transactions() = ledger.transactions()

    override fun toString() = "account {owner: $owner, balance: ${balance()}}"

}

