package com.javaforgood.goodandgenerous.data.repository;

import java.util.Optional;

import com.javaforgood.goodandgenerous.data.model.Publisher;
import com.javaforgood.goodandgenerous.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	Optional<Publisher> findByUser(User user);

}
