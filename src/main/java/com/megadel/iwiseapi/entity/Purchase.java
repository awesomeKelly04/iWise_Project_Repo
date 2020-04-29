package com.megadel.iwiseapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.megadel.iwiseapi.entity.audit.DateAudit;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchases")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Purchase extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="business_id")
    private Business business;

    @Column(name = "total_amount")
    private double totalAmountPerTimestamp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="purchase",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Item> items;

    public Purchase() {
    }

    public Purchase(double totalAmountPerTimestamp) {
        this.totalAmountPerTimestamp = totalAmountPerTimestamp;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item tempItem) {

        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(tempItem);

        tempItem.setPurchase(this);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", totalAmountPerTimestamp=" + totalAmountPerTimestamp +
                '}';
    }
}
