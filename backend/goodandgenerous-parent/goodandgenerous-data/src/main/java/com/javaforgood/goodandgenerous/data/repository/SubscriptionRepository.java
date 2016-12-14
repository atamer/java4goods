package com.javaforgood.goodandgenerous.data.repository;

import java.util.List;

import com.javaforgood.goodandgenerous.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaforgood.goodandgenerous.data.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	List<Subscription> findByCategory(Category category);
}
