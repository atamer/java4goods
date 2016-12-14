package com.javaforgood.goodandgenerous.data.repository;

import com.javaforgood.goodandgenerous.data.model.Publisher;
import com.javaforgood.goodandgenerous.data.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by atamer on 10/12/2016.
 */
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
