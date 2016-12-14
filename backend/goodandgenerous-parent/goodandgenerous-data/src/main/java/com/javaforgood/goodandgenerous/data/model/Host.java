package com.javaforgood.goodandgenerous.data.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by atamer on 10/12/2016.
 */
@Entity
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String location;


    @ManyToOne(optional = false)
    private Generous generous;


    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private Set<SkillLevel> skillLevelSet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Generous getGenerous() {
        return generous;
    }

    public void setGenerous(Generous generous) {
        this.generous = generous;
    }

    public Set<SkillLevel> getSkillLevelSet() {
        return skillLevelSet;
    }

    public void setSkillLevelSet(Set<SkillLevel> skillLevelSet) {
        this.skillLevelSet = skillLevelSet;
    }
}
