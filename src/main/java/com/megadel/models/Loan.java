package com.megadel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.megadel.models.audit.DateAudit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "loans")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Loan extends DateAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private double budgetAmount;

    @Column(name = "date_collected")
    private Date dateCollected;

    @Column(name = "duration")
    private String duration;

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="business_id")
    private Business business;

    public Loan() {
    }

    public Loan(double budgetAmount, Date dateCollected, String duration, Date dueDate) {
        this.budgetAmount = budgetAmount;
        this.dateCollected = dateCollected;
        this.duration = duration;
        this.dueDate = dueDate;
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

    public Date getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(Date dateCollected) {
        this.dateCollected = dateCollected;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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
        return "Loan{" +
                "id=" + id +
                ", budgetAmount=" + budgetAmount +
                ", dateCollected=" + dateCollected +
                ", duration='" + duration + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
