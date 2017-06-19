package io.aksahli.kata.bank

import io.aksahli.kata.bank.TransactionType.DEPOSIT

class Deposit(amount: Double, from: String): Transaction(type = DEPOSIT, amount = amount, from = from) {

    override fun amount() = amount

}
