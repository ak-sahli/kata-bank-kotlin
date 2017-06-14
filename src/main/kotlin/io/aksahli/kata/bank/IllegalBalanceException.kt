package io.aksahli.kata.bank

data class IllegalBalanceException(val requestedAmount: Amount, val currentBalance: Amount)
    : IllegalStateException("Requested amount ($requestedAmount) exceeds the current balance ($currentBalance)")