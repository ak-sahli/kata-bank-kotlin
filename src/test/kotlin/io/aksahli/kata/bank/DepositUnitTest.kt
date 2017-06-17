package io.aksahli.kata.bank

import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

object DepositUnitTest : SubjectSpek<Account>({

    given("a bank account") {

        subject { Account(owner = "Bruce Wayne", initialAmount = 1000.00) }

        on("deposit a valid amount of money") {
            subject.deposit(500.00)
            it("should increase the balance after a deposit of money") {
                assertEquals(expected = 1500.00, actual = subject.balance())
            }
            it("should record a deposit transaction") {
                assertEquals(expected = 2, actual = subject.transactions().size)
            }
        }

        on("deposit an invalid amount of money") {
            val depositException = assertFails { subject.deposit(-500.00) }
            it("should throw an illegal amount exception") {
                assertTrue { depositException is IllegalAmountException }
            }
            it("should keep the balance as is") {
                assertEquals(1000.00, subject.balance())
            }
            it("should not record a deposit transaction") {
                assertEquals(expected = 1, actual = subject.transactions().size)
            }
        }

    }

})

