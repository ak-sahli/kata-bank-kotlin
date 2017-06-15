package io.aksahli.kata.bank

data class Account(val owner: String, val initialAmount: Amount = 0.0) {

    var balance:Amount
        private set

    init {
        ensureAmountIsPositive(initialAmount)
        this.balance = initialAmount
    }

    infix fun deposit(amount: Amount) {
        ensureAmountIsPositive(amount)
        this.balance = this.balance + amount
    }

    infix fun  withdraw(requestedAmount: Amount) {
        ensureAmountIsPositive(requestedAmount)
        ensureBalanceIsSufficient(requestedAmount)
        this.balance = this.balance - requestedAmount
    }

    private fun ensureAmountIsPositive(amount: Amount) {
        if (amount < 0) {
            throw IllegalAmountException(requestedAmount = amount)
        }
    }

    private fun ensureBalanceIsSufficient(requestedAmount: Amount) {
        if (requestedAmount > this.balance) {
            throw IllegalBalanceException(requestedAmount, this.balance)
        }
    }

    fun transfer(amount: Amount, payeeAccount: Account) {
        try {
            this.withdraw(amount)
            payeeAccount.deposit(amount)
        } catch ( exception: Exception ) {
            when(exception) {
                is IllegalAmountException,
                is IllegalBalanceException ->  throw IllegalTransferException (
                        amount = amount,
                        payer = this,
                        payee = payeeAccount,
                        cause = exception
                )
                else -> throw exception
            }
        }
    }

}
