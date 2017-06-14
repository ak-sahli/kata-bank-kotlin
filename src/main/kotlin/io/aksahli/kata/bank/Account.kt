package io.aksahli.kata.bank

data class Account(val owner: String, val initialAmount: Amount = 0.0) {

    private var balance = initialAmount

    private fun validateAmount(requestedAmount: Amount) {
        if (requestedAmount < 0) {
            throw IllegalAmountException(requestedAmount = requestedAmount)
        }
    }

    private fun validateBalance(requestedAmount: Amount) {
        if (requestedAmount >= this.balance) {
            throw IllegalBalanceException(requestedAmount = requestedAmount, currentBalance = balance)
        }
    }

    infix fun deposit(amount: Amount) {
        validateAmount(amount)
        this.balance = this.balance + amount
    }

    infix fun  withdraw(amount: Amount) {
        validateAmount(amount)
        validateBalance(amount)
        this.balance = this.balance - amount
    }

    fun balance(): Amount = this.balance

}