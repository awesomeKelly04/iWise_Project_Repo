package com.megadel.iwise.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "spending_tracker")
public class SpendingTracker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="budget_plan_id")
    private BudgetPlan budgetPlan;

    @Column(name = "total_amount")
    private double totalAmountPerTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="spendingTracker",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Item> items;

    public SpendingTracker() {
    }

    public SpendingTracker(double totalAmountPerTimestamp) {
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
        this.timestamp = new Timestamp(new Date().getTime());
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // add convenience methods for bi-directional relationship

    public void add(Item tempItem) {

        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(tempItem);

        tempItem.setSpendingTracker(this);
    }

    @Override
    public String toString() {
        return "SpendingTracker{" +
                "id=" + id +
                ", totalAmountPerTimestamp=" + totalAmountPerTimestamp +
                ", timestamp=" + timestamp +
                '}';
    }
}
