package io.aksahli.kata.bank

import io.aksahli.kata.bank.TransactionType.*

class Account(val owner: String, initialAmount: Double = 0.0) {

    val ledger = Ledger()

    init { deposit(initialAmount) }

    fun deposit(amount: Double, from: String = "") {
        ledger.record(Deposit(amount, from))
    }

    fun withdraw(requestedAmount: Double, to: String = "") {
        if (requestedAmount > this.balance()) {
            throw InsufficientBalanceException(requestedAmount, this.balance())
        }
        ledger.record(Withdraw(requestedAmount, to))
    }

    fun transfer(amount: Double, payeeAccount: Account) {
        withdraw(requestedAmount = amount, to = payeeAccount.owner)
        payeeAccount.deposit(amount = amount, from = owner)
    }

    fun balance() = ledger.balance()

    fun transactions() = ledger.transactions()
    fun transactionsTo(toAccount: String) = ledger.transactions(WITHDRAW, toAccount)
    fun transactionsFrom(fromAccount: String) = ledger.transactions(DEPOSIT, fromAccount)

    override fun toString() = "account {owner: $owner, balance: ${balance()}}"

}

