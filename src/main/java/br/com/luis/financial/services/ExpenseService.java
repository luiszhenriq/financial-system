package br.com.luis.financial.services;

import br.com.luis.financial.domain.expense.ExpenseDTO;
import br.com.luis.financial.domain.expense.ExpenseResponseDTO;
import br.com.luis.financial.domain.expense.ExpenseUpdateDTO;
import br.com.luis.financial.domain.user.UserViewDTO;
import br.com.luis.financial.infra.exception.IdNotFoundException;
import br.com.luis.financial.models.Expense;
import br.com.luis.financial.models.User;
import br.com.luis.financial.repositories.ExpenseRepository;
import br.com.luis.financial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseResponseDTO createExpense(ExpenseDTO data) {
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new IdNotFoundException("Usuário não foi encontrado!"));

        Expense newExpense = new Expense(data);
        newExpense.setUser(user);
        Expense saveExpense = repository.save(newExpense);

        return new ExpenseResponseDTO(saveExpense.getId(), data.name(), data.datePayment(), data.status(), data.value(),
                new UserViewDTO(saveExpense.getUser().getId(),saveExpense.getUser().getUsername()));
    }

    public Page<ExpenseResponseDTO> findAll(Pageable pageable) {
        Page<Expense> expensePage = repository.findAll(pageable);

        List<ExpenseResponseDTO> expenseResponseList = expensePage.getContent().stream()
                .map(expense -> new ExpenseResponseDTO(
                        expense.getId(),
                        expense.getName(),
                        expense.getDatePayment(),
                        expense.getStatus(),
                        expense.getValue(),
                        new UserViewDTO(expense.getUser().getId(), expense.getUser().getUsername())
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(expenseResponseList, pageable, expensePage.getTotalElements());
    }


    public Optional<ExpenseResponseDTO> findById(Long id) {
        Optional<Expense> expenseOptional = repository.findById(id);
        return expenseOptional.map(this::mapExpense);
    }


    public ExpenseResponseDTO update(Long id , ExpenseUpdateDTO updateDTO) {
        Expense newExpense = repository.findById(id).get();
        newExpense.setName(updateDTO.name());
        newExpense.setStatus(updateDTO.status());
        newExpense.setValue(updateDTO.value());
        Expense updatedExpense = repository.save(newExpense);
        return new ExpenseResponseDTO(updatedExpense.getId(), updateDTO.name(), updateDTO.datePayment(), updateDTO.status(), updateDTO.value(),
                new UserViewDTO(updatedExpense.getUser().getId(),updatedExpense.getUser().getUsername()));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ExpenseResponseDTO> findByName(String name) {
        List<Expense> expenses = repository.findByName(name);
        return expenses.stream()
                .map(this::mapExpense)
                .collect(Collectors.toList());
    }

    private ExpenseResponseDTO mapExpense(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getName(),
                expense.getDatePayment(),
                expense.getStatus(),
                expense.getValue(),
                new UserViewDTO(expense.getUser().getId(), expense.getUser().getUsername())
        );
    }


}
