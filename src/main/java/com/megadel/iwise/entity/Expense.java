package com.megadel.iwise.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expenses")
public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY,
            cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="business_id")
    private Business business;

    @Column(name = "total_amount")
    private double totalAmountPerTimestamp;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="expense",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Item> items;

    public Expense() {
    }

    public Expense(double totalAmountPerTimestamp) {
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public double getTotalAmountPerTimestamp() {
        return totalAmountPerTimestamp;
    }

    public void setTotalAmountPerTimestamp(double totalAmountPerTimestamp) {
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
    }

    public Timestamp getTimestamp() {
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

        tempItem.setExpense(this);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", business=" + business +
                ", totalAmountPerTimestamp=" + totalAmountPerTimestamp +
                ", timestamp=" + timestamp +
                '}';
    }
}
