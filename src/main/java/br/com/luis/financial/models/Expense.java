package br.com.luis.financial.models;

import br.com.luis.financial.domain.expense.ExpenseDTO;
import br.com.luis.financial.domain.expense.ExpenseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_payment")
    private LocalDate datePayment;

    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;

    private Double value;

    public Expense(ExpenseDTO data) {
        this.name = data.name();
        this.datePayment = data.datePayment();
        this.status = data.status();
        this.value = data.value();
    }
}
