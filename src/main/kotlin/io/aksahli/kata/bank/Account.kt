package io.aksahli.kata.bank

class Account(val owner: String,initialAmount: Double = 0.0) {

    var balance: Double
        private set

    init {
        ensureAmountIsPositive(initialAmount)
        this.balance = initialAmount
    }

    fun deposit(amount: Double) {
        ensureAmountIsPositive(amount)
        this.balance = this.balance + amount
    }

    fun withdraw(requestedAmount: Double) {
        ensureAmountIsPositive(requestedAmount)
        ensureBalanceIsSufficient(requestedAmount)
        this.balance = this.balance - requestedAmount
    }

    fun transfer(amount: Double, payeeAccount: Account) {
        this.withdraw(amount)
        payeeAccount.deposit(amount)
    }

    override fun toString() = "account {owner: $owner, balance: $balance}"

    private fun ensureAmountIsPositive(amount: Double) {
        if (amount < 0) {
            throw IllegalAmountException(requestedAmount = amount)
        }
    }

    private fun ensureBalanceIsSufficient(requestedAmount: Double) {
        if (requestedAmount > this.balance) {
            throw InsufficientBalanceException(requestedAmount, this.balance)
        }
    }

}
