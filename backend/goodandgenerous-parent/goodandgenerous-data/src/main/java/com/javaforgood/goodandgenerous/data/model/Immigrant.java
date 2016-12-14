package com.javaforgood.goodandgenerous.data.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by atamer on 10/12/2016.
 */
@Entity
public class Immigrant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Integer age;


    @OneToMany(mappedBy = "immigrant1", cascade = CascadeType.ALL)
    private Set<ImmigrantRelative> relatives;

    @OneToMany(mappedBy = "immigrant", cascade = CascadeType.ALL)
    private Set<SkillLevel> skillLevelSet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<ImmigrantRelative> getRelatives() {
        return relatives;
    }

    public void setRelatives(Set<ImmigrantRelative> relatives) {
        this.relatives = relatives;
    }

    public Set<SkillLevel> getSkillLevelSet() {
        return skillLevelSet;
    }

    public void setSkillLevelSet(Set<SkillLevel> skillLevelSet) {
        this.skillLevelSet = skillLevelSet;
    }
}
