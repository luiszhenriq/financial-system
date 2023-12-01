package br.com.luis.financial.services;

import br.com.luis.financial.domain.expense.ExpenseDTO;
import br.com.luis.financial.domain.expense.ExpenseUpdateDTO;
import br.com.luis.financial.infra.exception.IdNotFoundException;
import br.com.luis.financial.models.Expense;
import br.com.luis.financial.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public Expense createExpense(ExpenseDTO data) {
        Expense newExpense = new Expense(data);
        return repository.save(newExpense);
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Optional<Expense> findById(Long id) {
       return repository.findById(id);
    }

    public Expense update(Long id , ExpenseUpdateDTO updateDTO) {
        Expense newExpense = repository.findById(id).get();
        newExpense.setName(updateDTO.name());
        newExpense.setStatus(updateDTO.status());
        newExpense.setValue(updateDTO.value());
        return repository.save(newExpense);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
