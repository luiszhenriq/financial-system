package br.com.luis.financial.domain.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExpenseUpdateDTO(
        @NotBlank(message = "Nome é um campo obrigatório!")
        String name,

        @NotNull(message = "Data de pagamento é um campo obrigatório!")
        LocalDate datePayment,
        @NotNull(message = "Status é um campo obrigatório!")
        ExpenseStatus status,
        @NotNull(message = "Valor é um campo obrigatório!")
        Double value) {
}
