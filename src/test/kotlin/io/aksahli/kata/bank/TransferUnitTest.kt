package io.aksahli.kata.bank

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

object TransferUnitTest : Spek({

    given("a payer and a payee") {
        var payerAccount = Account("Bruce Wayne")
        var payeeAccount = Account("Clark Kent")

        beforeEachTest {
            payerAccount.deposit(1000.00)
            payeeAccount.deposit(1000.00)
        }

        afterEachTest {
            payerAccount.withdraw(payerAccount.balance)
            payeeAccount.withdraw(payeeAccount.balance)
        }

        on("a transfer of a valid amount of money") {
            payerAccount.transfer(500.00, payeeAccount)
            it("should withdraw the amount from the payer account") {
                assertEquals(500.00, payerAccount.balance)
            }
            it("should deposit the amount in the payee account") {
                assertEquals(1500.00, payeeAccount.balance)
            }
        }

        on("a transfer of an invalid amount of money") {
            val transferException = assertFails { payerAccount.transfer(-500.00, payeeAccount) }
            it("should throw an illegal transfer exception") {
                assertTrue { transferException is IllegalAmountException }
            }
            it("should not withdraw money from the payer account") {
                assertEquals(1000.00, payerAccount.balance)
            }
            it("should not deposit money in the payee account") {
                assertEquals(1000.00, payeeAccount.balance)
            }
        }

        on("a transfer of an amount of money that exceeds the payer balance") {
            val transferException = assertFails { payerAccount.transfer(1500.00, payeeAccount) }
            it("should throw an illegal transfer exception") {
                assertTrue { transferException is InsufficientBalanceException }
            }
            it("should not withdraw money from the payer account") {
                assertEquals(1000.00, payerAccount.balance)
            }
            it("should not deposit money in the payee account") {
                assertEquals(1000.00, payeeAccount.balance)
            }
        }
    }


})

