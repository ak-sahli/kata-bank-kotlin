package io.aksahli.kata.bank

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class WithdrawUnitTest : SubjectSpek<Account>({

    describe("a bank account") {

        subject { Account(owner = "Bruce Wayne", initialAmount = 1000.00) }

        it("should decrease the balance after a withdrawal of a valid requestedAmount of money") {
            subject withdraw 500.00
            assertEquals(500.00, subject.balance)
        }

        it("should throw an error after a withdrawal of an invalid requested amount of money") {
            assertFailsWith(IllegalAmountException::class) {
                subject withdraw  -500.00
            }
        }

        it("should throw an error after a withdrawal of an requested amount of money that exceeds the balance") {
            assertFailsWith(IllegalBalanceException::class) {
                subject withdraw  1001.00
            }
        }

    }

})
