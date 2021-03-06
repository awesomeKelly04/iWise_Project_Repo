package com.megadel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.megadel.models.audit.DateAudit;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wallets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wallet extends DateAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "point")
    private int point;

    @Column(name = "naira")
    private double naira;

    @Column(name = "celo_dollar")
    private double celoDollar;

    @OneToOne(fetch = FetchType.LAZY, mappedBy="wallet", cascade=CascadeType.ALL)
    private User user;

    public Wallet() {
    }

    public Wallet(int point, double naira, double celoDollar) {
        this.point = point;
        this.naira = naira;
        this.celoDollar = celoDollar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getNaira() {
        return naira;
    }

    public void setNaira(double naira) {
        this.naira = naira;
    }

    public double getCeloDollar() {
        return celoDollar;
    }

    public void setCeloDollar(double celoDollar) {
        this.celoDollar = celoDollar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", point=" + point +
                ", naira=" + naira +
                ", celoDollar=" + celoDollar +
                '}';
    }
}
