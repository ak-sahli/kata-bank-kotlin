package io.aksahli.kata.bank

import java.util.*

data class Transaction(val type: TransactionType, val amount: Double, val date: Date = Date())