package br.com.luis.financial.controller;

import br.com.luis.financial.domain.expense.ExpenseDTO;
import br.com.luis.financial.domain.expense.ExpenseResponseDTO;
import br.com.luis.financial.domain.expense.ExpenseUpdateDTO;
import br.com.luis.financial.infra.exception.IdNotFoundException;
import br.com.luis.financial.models.Expense;
import br.com.luis.financial.repositories.ExpenseRepository;
import br.com.luis.financial.services.ExpenseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody @Valid ExpenseDTO dto) {
        ExpenseResponseDTO newExpense = service.createExpense(dto);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseResponseDTO>> findAllExpenses(Pageable pageable) {
        Page<ExpenseResponseDTO> expenses = service.findAll(pageable);

        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> findExpenseById(@PathVariable Long id) {
        Optional<ExpenseResponseDTO> expenseOptional = service.findById(id);

        if (expenseOptional.isPresent()) {
            ExpenseResponseDTO expense = expenseOptional.get();
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } else throw new IdNotFoundException("Despesa não encontrada com o ID: " + id);
    }

    @GetMapping("/name")
    public ResponseEntity<List<ExpenseResponseDTO>> findExpenseByName(@RequestParam String name) {
        List<ExpenseResponseDTO> expense = service.findByName(name);

        if (expense.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable Long id, @RequestBody @Valid ExpenseUpdateDTO updateDTO){
        ExpenseResponseDTO updatedExpense = service.update(id, updateDTO);
        return ResponseEntity.ok().body(updatedExpense);
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
