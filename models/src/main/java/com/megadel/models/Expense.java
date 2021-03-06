package com.megadel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.megadel.models.audit.DateAudit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expenses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Expense extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="budget_id")
    private BudgetPlan budgetPlan;

    @Column(name = "total_amount")
    private double totalAmountPerTimestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="expense",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Item> items;

    public Expense() {
    }

    public Expense(double totalAmountPerTimestamp) {
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BudgetPlan getBudgetPlan() {
        return budgetPlan;
    }

    public void setBudgetPlan(BudgetPlan budgetPlan) {
        this.budgetPlan = budgetPlan;
    }

    public double getTotalAmountPerTimestamp() {
        return totalAmountPerTimestamp;
    }

    public void setTotalAmountPerTimestamp(double totalAmountPerTimestamp) {
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // add convenience methods for bi-directional relationship

    public void addItem(Item tempItem) {

        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(tempItem);

        tempItem.setExpense(this);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", totalAmountPerTimestamp=" + totalAmountPerTimestamp +
                '}';
    }
}
