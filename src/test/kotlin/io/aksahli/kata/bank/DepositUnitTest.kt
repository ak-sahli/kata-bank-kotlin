package io.aksahli.kata.bank

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

object DepositUnitTest : SubjectSpek<Account>({

    describe("a bank account") {

        subject { Account(owner = "Bruce Wayne", initialAmount = 1000.00) }

        it("should increase the balance after a deposit of money") {
            subject deposit 500.00
            assertEquals(expected = 1500.00, actual = subject.balance)
        }

        it("should throw an exception after a deposit of an invalid amount of money") {
            assertFailsWith(IllegalAmountException::class) {
                subject deposit -500.00
            }
        }

    }

})