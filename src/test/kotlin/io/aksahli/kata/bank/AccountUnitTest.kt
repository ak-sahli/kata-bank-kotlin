package io.aksahli.kata.bank

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

object AccountUnitTest : SubjectSpek<Account>({

    describe("a bank account") {

        subject { Account(owner = "Bruce Wayne", initialAmount = 1000.00) }

        on("deposit") {
            it("should increase the balance after a deposit of a valid requestedAmount of money") {
                subject deposit 500.00
                assertEquals(1500.00, subject.balance())
            }
            it("should throw an error after a withdrawal of an invalid requestedAmount of money") {
                assertFailsWith(IllegalAmountException::class) {
                    subject deposit -500.00
                }
            }
        }

        on("withdraw") {
            it("should decrease the balance after a withdrawal of a valid requestedAmount of money") {
                subject withdraw 500.00
                assertEquals(500.00, subject.balance())
            }
            it("should throw an error after a withdrawal of an invalid requestedAmount of money") {
                assertFailsWith(IllegalAmountException::class) {
                    subject withdraw  -500.00
                }
            }
            it("should throw an error after a withdrawal of an requestedAmount of money that exceeds the balance") {
                assertFailsWith(IllegalBalanceException::class) {
                    subject withdraw  50000.00
                }
            }
        }

    }

})

typealias Amount = Double

data class IllegalAmountException(val requestedAmount: Amount)
    : IllegalArgumentException("Invalid requested requestedAmount ($requestedAmount)")

data class IllegalBalanceException(val requestedAmount: Amount, val currentBalance: Amount)
    : IllegalStateException("Requested amount ($requestedAmount) exceeds the current balance ($currentBalance)")

data class Account(val owner: String, val initialAmount: Amount = 0.0) {

    private var balance = initialAmount

    private fun validateAmount(requestedAmount: Amount) {
        if (requestedAmount < 0) {
            throw IllegalAmountException(requestedAmount = requestedAmount)
        }
    }

    private fun validateBalance(requestedAmount: Amount) {
        if (requestedAmount >= this.balance) {
            throw IllegalBalanceException(requestedAmount = requestedAmount, currentBalance = balance)
        }
    }

    infix fun deposit(amount: Amount) {
        validateAmount(amount)
        this.balance = this.balance + amount
    }

    infix fun  withdraw(amount: Amount) {
        validateAmount(amount)
        validateBalance(amount)
        this.balance = this.balance - amount
    }

    fun balance(): Amount = this.balance

}
