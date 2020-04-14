package com.megadel.iwise.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "budget_plan")
public class BudgetPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "period")
    private Enum period;

    @Column(name = "budget_amount")
    private double budgetAmount;

    @ManyToOne(fetch=FetchType.LAZY,
               cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="item_id")
    private List<Item> items;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public BudgetPlan() {
    }

    public BudgetPlan(Enum period, double budgetAmount, List<Item> items) {
        this.period = period;
        this.budgetAmount = budgetAmount;
        this.items = items;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public void add (Item tempItem){
        if (items == null){
            items = new ArrayList<>();
        }

        items.add(tempItem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enum getPeriod() {
        return period;
    }

    public void setPeriod(Enum period) {
        this.period = period;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BudgetPlan{" +
                "id=" + id +
                ", period=" + period +
                ", budgetAmount=" + budgetAmount +
                ", user=" + user +
                ", items=" + items +
                ", timestamp=" + timestamp +
                '}';
    }
}
