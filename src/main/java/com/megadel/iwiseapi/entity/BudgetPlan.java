package com.megadel.iwiseapi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "budget_plan")
public class BudgetPlan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "period")
    private String period;

    @Column(name = "budget_amount")
    private double budgetAmount;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="budgetPlan",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Item> items;
    
    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="budgetPlan",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<SpendingTracker> spendingTrackers;

    public BudgetPlan() {
    }

    public BudgetPlan(String period, double budgetAmount) {
        this.period = period;
        this.budgetAmount = budgetAmount;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
    
    public List<SpendingTracker> getSpendingTrackers() {
		return spendingTrackers;
	}

	public void setSpendingTrackers(List<SpendingTracker> spendingTrackers) {
		this.spendingTrackers = spendingTrackers;
	}
    
    // add convenience methods for bi-directional relationship
    
	public void add(Item tempItem) {

        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(tempItem);

        tempItem.setBudgetPlan(this);
    }
    
    public void add(SpendingTracker tempSpendingTracker) {

        if (spendingTrackers == null) {
            spendingTrackers = new ArrayList<>();
        }

        spendingTrackers.add(tempSpendingTracker);

        tempSpendingTracker.setBudgetPlan(this);
    }

    @Override
    public String toString() {
        return "BudgetPlan{" +
                "id=" + id +
                ", period=" + period +
                ", budgetAmount=" + budgetAmount +
                ", timestamp=" + timestamp +
                '}';
    }
}
