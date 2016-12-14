package com.javaforgood.goodandgenerous.rest;

import java.util.List;


import com.javaforgood.goodandgenerous.data.model.Category;
import com.javaforgood.goodandgenerous.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/rest/category")
public class CategoryRestService {

	@Autowired
	private CategoryRepository repository;


	@RequestMapping(value = "")
	public List<Category> getCategories() {
		return repository.findAll();
	}

}
