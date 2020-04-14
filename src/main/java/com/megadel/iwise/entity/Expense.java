package com.megadel.iwise.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY,
            cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="business_id")
    private Business business;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="item_id")
    private List<Item> items;

    @Column(name = "total_amount")
    private double totalAmountPerTimestamp;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public Expense() {
    }

    public Expense(List<Item> items, double totalAmountPerTimestamp) {
        this.items = items;
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", business=" + business +
                ", items=" + items +
                ", totalAmountPerTimestamp=" + totalAmountPerTimestamp +
                ", timestamp=" + timestamp +
                '}';
    }
}
