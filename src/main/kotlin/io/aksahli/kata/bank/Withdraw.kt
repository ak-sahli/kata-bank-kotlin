package io.aksahli.kata.bank

import io.aksahli.kata.bank.TransactionType.*

class Withdraw(amount: Double, to:String): Transaction(type = WITHDRAW, amount = amount, to = to) {

    override fun amount(): Double = -amount

}
