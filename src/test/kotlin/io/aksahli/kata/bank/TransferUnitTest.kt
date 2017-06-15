package io.aksahli.kata.bank

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

object TransferUnitTest : Spek({



    describe("a transfer between two bank accounts") {
        given("a payer and a payee") {
            val payerAccount = Account(owner = "Bruce Wayne", initialAmount = 1000.00)
            val payeeAccount = Account(owner = "Clark Kent", initialAmount = 1000.00)
            on("a transfer of a valid amount of money") {
                payerAccount.transfer(500.00).to(payeeAccount)
                it("should withdraw the amount from the payer account") {
                    assertEquals(500.00, payerAccount.balance)
                }
                it("should deposit the amount in the payee account") {
                    assertEquals(1500.00, payeeAccount.balance)
                }
            }
        }
        given("a payer and a payee") {
            val payerAccount = Account(owner = "Bruce Wayne", initialAmount = 1000.00)
            val payeeAccount = Account(owner = "Clark Kent", initialAmount = 1000.00)
            on("a transfer of an invalid amount of money") {
                assertFailsWith(IllegalTransferException::class) {
                    payerAccount.transfer(-500.00).to(payeeAccount)
                }
                it("should not withdraw money from the payer account") {
                    assertEquals(1000.00, payerAccount.balance)
                }
                it("should not deposit money in the payee account") {
                    assertEquals(1000.00, payeeAccount.balance)
                }
            }
        }
        given("a payer and a payee") {
            val payerAccount = Account(owner = "Bruce Wayne", initialAmount = 1000.00)
            val payeeAccount = Account(owner = "Clark Kent", initialAmount = 1000.00)
            on("a transfer of an amount of money that exceeds the payer balance") {
                assertFailsWith(IllegalTransferException::class) {
                    payerAccount.transfer(1500.00).to(payeeAccount)
                }
                it("should not withdraw money from the payer account") {
                    assertEquals(1000.00, payerAccount.balance)
                }
                it("should not deposit money in the payee account") {
                    assertEquals(1000.00, payeeAccount.balance)
                }
            }
        }
    }

})

