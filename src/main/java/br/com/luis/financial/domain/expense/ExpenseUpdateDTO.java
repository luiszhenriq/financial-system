package br.com.luis.financial.domain.expense;

public record ExpenseUpdateDTO(String name, ExpenseStatus status, Double value) {
}
