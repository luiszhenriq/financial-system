package br.com.luis.financial.repositories;

import br.com.luis.financial.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(value = "select u from Expense u where u.name like %?1%")
    List<Expense> findByName(String name);
}
