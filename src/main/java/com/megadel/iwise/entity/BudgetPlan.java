package com.megadel.iwise.entity;

import javax.persistence.*;
<<<<<<< HEAD

import com.megadel.iwise.projectenum.Period;

import java.io.Serializable;
=======
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "budget_plan")
<<<<<<< HEAD
public class BudgetPlan implements Serializable {
=======
public class BudgetPlan {
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "period")
<<<<<<< HEAD
    private String period;
=======
    private Enum period;
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7

    @Column(name = "budget_amount")
    private double budgetAmount;

<<<<<<< HEAD
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "timestamp")
    private Timestamp timestamp;

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

=======
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

>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
=======
    public Enum getPeriod() {
        return period;
    }

    public void setPeriod(Enum period) {
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7
        this.period = period;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

<<<<<<< HEAD
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
=======
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

<<<<<<< HEAD
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
=======
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7
    }

    @Override
    public String toString() {
        return "BudgetPlan{" +
                "id=" + id +
                ", period=" + period +
                ", budgetAmount=" + budgetAmount +
                ", user=" + user +
<<<<<<< HEAD
=======
                ", items=" + items +
>>>>>>> 35874cb975530fd693c5f2363a427426c1a822b7
                ", timestamp=" + timestamp +
                '}';
    }
}
