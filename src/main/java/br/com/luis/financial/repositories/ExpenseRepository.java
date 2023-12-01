package br.com.luis.financial.repositories;

import br.com.luis.financial.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
