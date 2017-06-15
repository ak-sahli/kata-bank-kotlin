package io.aksahli.kata.bank

class IllegalTransferException(amount: Amount, payer: Account, payee: Account, cause: Throwable)
    : RuntimeException("Failed to transfer $amount from $payer to $payee", cause)