package io.aksahli.kata.bank

enum class TransactionType(val value: (Double) -> Double) {
    DEPOSIT({ value -> value }), WITHDRAW({ value -> -value })
}