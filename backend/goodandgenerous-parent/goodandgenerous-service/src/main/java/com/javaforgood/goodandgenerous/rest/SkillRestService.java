package com.javaforgood.goodandgenerous.rest;

import com.javaforgood.goodandgenerous.data.repository.SkillRepository;
import com.javaforgood.goodandgenerous.service.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by atamer on 10/12/2016.
 */

@RestController
@RequestMapping("/rest/public/skills")
public class SkillRestService {

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping(value = "")
    public ResponseEntity<Object> skills() {
        return ResponseEntity.ok(skillRepository.findAll());
    }
}
