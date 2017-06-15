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

        on("deposit a valid amount of money") {
            subject deposit 500.00
            it("should increase the balance after a deposit of a valid requested amount of money") {
                assertEquals(expected = 1500.00, actual = subject.balance)
            }
        }


        it("should throw an error after a deposit of an invalid requested amount of money") {
            assertFailsWith(IllegalAmountException::class) {
                subject deposit -500.00
            }
        }

    }

})