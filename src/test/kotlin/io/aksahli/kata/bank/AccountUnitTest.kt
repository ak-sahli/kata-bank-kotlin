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
            it("should increase the balance after a deposit of a valid amount of money") {
                subject deposit 500.00
                assertEquals(1500.00, subject.balance())
            }
            it("should throw an error after a withdrawal of an invalid amount of money") {
                assertFailsWith(IllegalAmountException::class) {
                    subject deposit -500.00
                }
            }
        }

        on("withdraw") {
            it("should decrease the balance after a withdrawal of a valid amount of money") {
                subject withdraw 500.00
                assertEquals(500.00, subject.balance())
            }
            it("should throw an error after a withdrawal of an invalid amount of money") {
                assertFailsWith(IllegalAmountException::class) {
                    subject withdraw  -500.00
                }
            }
        }

    }

})

typealias Amount = Double

data class IllegalAmountException(val amount: Amount)
    : IllegalArgumentException("Invalid requested amount: $amount")

data class Account(val owner: String, val initialAmount: Amount) {

    private var balance = initialAmount

    private fun validateAmount(amount: Amount) {
        if (amount < 0) {
            throw IllegalAmountException(amount)
        }
    }

    infix fun deposit(amount: Amount) {
        validateAmount(amount)
        this.balance = this.balance + amount
    }

    infix fun  withdraw(amount: Amount) {
        validateAmount(amount)
        this.balance = this.balance - amount
    }

    fun balance(): Amount = this.balance

}
