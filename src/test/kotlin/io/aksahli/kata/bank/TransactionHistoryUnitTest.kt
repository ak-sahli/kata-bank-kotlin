package io.aksahli.kata.bank

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object TransactionHistoryUnitTest : Spek ({

    given("two bank accounts") {
        var firstAccount = Account("Bruce Wayne", 50000.00)
        var secondAccount = Account("Clark Kent", 50000.00)

        beforeEachTest {
            firstAccount.transfer(100.00, secondAccount)
            secondAccount.transfer(50.00, firstAccount)
            secondAccount.transfer(50.00, firstAccount)
            firstAccount.transfer(200.00, secondAccount)
            secondAccount.transfer(100.00, firstAccount)
            secondAccount.transfer(100.00, firstAccount)
        }

        on("querying the accounts for outgoing transfers history") {
            val firstAccountOutgoingTransfers = firstAccount.transactionsTo(toAccount = "Clark Kent")
            val secondAccountOutgoingTransfers = secondAccount.transactionsTo(toAccount = "Bruce Wayne")
            it("should have outgoing transfer to the second account") {
                assertEquals(expected = 2, actual = firstAccountOutgoingTransfers.size)
            }
            it("should have outgoing transfer to the first account") {
                assertEquals(expected = 4, actual = secondAccountOutgoingTransfers.size)
            }
        }

        on("querying the accounts for incoming transfers history") {
            val firstAccountIncomingTransfers = firstAccount.transactionsFrom(fromAccount = "Clark Kent")
            val secondAccountIncomingTransfers = secondAccount.transactionsFrom(fromAccount = "Bruce Wayne")
            it("should have incoming transfer from the second account") {
                assertEquals(expected = 4, actual = firstAccountIncomingTransfers.size)
            }
            it("should have incoming transfer from the first account") {
                assertEquals(expected = 2, actual = secondAccountIncomingTransfers.size)
            }
        }

        afterEachTest {
            firstAccount = Account("Bruce Wayne", 50000.00)
            secondAccount = Account("Clark Kent", 50000.00)
        }

    }

})
