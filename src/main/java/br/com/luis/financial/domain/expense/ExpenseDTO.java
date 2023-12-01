package br.com.luis.financial.domain.expense;


import java.time.LocalDate;

public record ExpenseDTO(String name, LocalDate datePayment, ExpenseStatus status, Double value) {
}
