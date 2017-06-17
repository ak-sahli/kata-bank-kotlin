package io.aksahli.kata.bank

data class InsufficientBalanceException(val requestedAmount: Double, val currentBalance: Double)
    : IllegalStateException("Requested amount ($requestedAmount) exceeds the current balance ($currentBalance)")