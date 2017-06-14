package io.aksahli.kata.bank

data class IllegalAmountException(val requestedAmount: Amount)
    : IllegalArgumentException("Invalid requested requestedAmount ($requestedAmount)")