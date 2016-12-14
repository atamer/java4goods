package com.javaforgood.goodandgenerous.data.model;

import javax.persistence.*;

/**
 * Created by atamer on 10/12/2016.
 */

@Entity
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false)
    private Host host;

    @ManyToOne(optional = false)
    private Immigrant immigrant;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Immigrant getImmigrant() {
        return immigrant;
    }

    public void setImmigrant(Immigrant immigrant) {
        this.immigrant = immigrant;
    }
}
