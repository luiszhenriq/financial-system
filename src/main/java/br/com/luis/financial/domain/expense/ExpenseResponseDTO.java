package br.com.luis.financial.domain.expense;

import br.com.luis.financial.domain.user.UserViewDTO;

import java.time.LocalDate;

public record ExpenseResponseDTO(Long id, String name, LocalDate datePayment, ExpenseStatus status, Double value, UserViewDTO user) {
}
