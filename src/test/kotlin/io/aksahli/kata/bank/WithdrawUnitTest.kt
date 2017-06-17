package io.aksahli.kata.bank

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

object WithdrawUnitTest : SubjectSpek<Account>({

    describe("a bank account") {

        subject { Account(owner = "Bruce Wayne", initialAmount = 1000.00) }

        on("withdraw a valid amount of money") {
            it("should decrease the balance after withdrawing money") {
                subject.withdraw(500.00)
                assertEquals(500.00, subject.balance())
            }
            it("should record a withdraw transaction") {
                assertEquals(expected = 2, actual = subject.transactions().size)
            }
        }

        on("withdraw an invalid amount of money") {
            val withdrawException = assertFails { subject.withdraw(-500.00) }
            it("should throw an illegal amount exception") {
                assertTrue { withdrawException is IllegalAmountException }
            }
            it("should keep the balance as is") {
                assertEquals(1000.00, subject.balance())
            }
            it("should not record a withdraw transaction") {
                assertEquals(expected = 1, actual = subject.transactions().size)
            }
        }

        on("withdraw an amount of money that exceeds the balance") {
            val withdrawException = assertFails { subject.withdraw(1001.00) }
            it("should throw an insufficient balance exception") {
               assertTrue { withdrawException is InsufficientBalanceException }
            }
            it("should keep the balance as is") {
                assertEquals(1000.00, subject.balance())
            }
            it("should not record a withdraw transaction") {
                assertEquals(expected = 1, actual = subject.transactions().size)
            }
        }

    }

})
