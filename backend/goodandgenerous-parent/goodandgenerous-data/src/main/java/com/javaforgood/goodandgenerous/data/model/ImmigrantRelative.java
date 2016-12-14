package com.javaforgood.goodandgenerous.data.model;

import javax.persistence.*;

/**
 * Created by atamer on 10/12/2016.
 */
@Entity
public class ImmigrantRelative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;


    @Column(name = "event_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne(optional = false)
    private Immigrant immigrant1;


    @ManyToOne(optional = false)
    private Immigrant immigrant2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Immigrant getImmigrant1() {
        return immigrant1;
    }

    public void setImmigrant1(Immigrant immigrant1) {
        this.immigrant1 = immigrant1;
    }

    public Immigrant getImmigrant2() {
        return immigrant2;
    }

    public void setImmigrant2(Immigrant immigrant2) {
        this.immigrant2 = immigrant2;
    }
}

