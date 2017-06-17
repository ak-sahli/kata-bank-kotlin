package io.aksahli.kata.bank

data class IllegalAmountException(val requestedAmount: Double)
    : IllegalArgumentException("Invalid requested amount ($requestedAmount)")