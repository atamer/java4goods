package com.javaforgood.goodandgenerous.data.model;

import javax.persistence.*;

/**
 * Created by atamer on 10/12/2016.
 */
@Entity
public class Money {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;



    @ManyToOne(optional = false)
    private Generous generous;

    @Column(nullable = false)
    private Double amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Generous getGenerous() {
        return generous;
    }

    public void setGenerous(Generous generous) {
        this.generous = generous;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
