package com.megadel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "investments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private double budgetAmount;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "duration")
    private String duration;

    @Column(name = "accumulated_amount")
    private double accumulatedAmount;

    @Column(name = "date_collected")
    private Date dateCollected;

    @Column(name = "date_end")
    private Date dateEnd;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="business_id")
    private Business business;

    public Investment() {
    }

    public Investment(double budgetAmount, double interestRate, String duration, double accumulatedAmount, Date dateCollected, Date dateEnd) {
        this.budgetAmount = budgetAmount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.accumulatedAmount = accumulatedAmount;
        this.dateCollected = dateCollected;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(double accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    public Date getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(Date dateCollected) {
        this.dateCollected = dateCollected;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "id=" + id +
                ", budgetAmount=" + budgetAmount +
                ", interestRate=" + interestRate +
                ", duration='" + duration + '\'' +
                ", accumulatedAmount=" + accumulatedAmount +
                ", dateCollected=" + dateCollected +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
