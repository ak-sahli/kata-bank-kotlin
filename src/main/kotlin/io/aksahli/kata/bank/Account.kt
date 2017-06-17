package io.aksahli.kata.bank

import io.aksahli.kata.bank.TransactionType.*

class Account(val owner: String, initialAmount: Double = 0.0) {

    val transactions: MutableList<Transaction> = mutableListOf()

    init {
        this.deposit(initialAmount)
    }

    fun deposit(amount: Double) {
        ensureAmountIsPositive(amount)
        this.transactions.add(Transaction(type = DEPOSIT, amount = amount))
    }

    fun withdraw(requestedAmount: Double) {
        ensureAmountIsPositive(requestedAmount)
        ensureBalanceIsSufficient(requestedAmount)
        this.transactions.add(Transaction(type = WITHDRAW, amount = requestedAmount))
    }

    fun transfer(amount: Double, payeeAccount: Account) {
        this.withdraw(amount)
        payeeAccount.deposit(amount)
    }

    fun balance() = transactions
            .map {
                when(it.type) {
                    DEPOSIT -> it.amount
                    WITHDRAW -> -it.amount
                }
            }
            .reduce { accumulatedAmount, amount -> amount + accumulatedAmount }

    fun transactions() = transactions.map { it }

    override fun toString() = "account {owner: $owner, balance: ${balance()}}"

    private fun ensureAmountIsPositive(amount: Double) {
        if (amount < 0) {
            throw IllegalAmountException(requestedAmount = amount)
        }
    }

    private fun ensureBalanceIsSufficient(requestedAmount: Double) {
        if (requestedAmount > this.balance()) {
            throw InsufficientBalanceException(requestedAmount, this.balance())
        }
    }

}

