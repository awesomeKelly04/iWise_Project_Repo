package com.megadel.iwise.entity;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {

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
