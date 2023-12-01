package br.com.luis.financial.controller;

import br.com.luis.financial.domain.expense.ExpenseDTO;
import br.com.luis.financial.domain.expense.ExpenseUpdateDTO;
import br.com.luis.financial.infra.exception.IdNotFoundException;
import br.com.luis.financial.models.Expense;
import br.com.luis.financial.repositories.ExpenseRepository;
import br.com.luis.financial.services.ExpenseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/financial")
@RestController
public class FinancialController {

    @Autowired
    private ExpenseService service;

    @Autowired
    private ExpenseRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseDTO dto) {
        Expense newExpense = service.createExpense(dto);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAllExpenses(){
        List<Expense> expenses = service.findAll();
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findExpenseById(@PathVariable Long id) {
        Optional<Expense> expenseOptional = service.findById(id);

        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } else throw new IdNotFoundException("Despesa não encontrada com o ID: " + id);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody ExpenseUpdateDTO updateDTO){
        Expense newExpense = service.update(id, updateDTO);
        return ResponseEntity.ok().body(newExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
