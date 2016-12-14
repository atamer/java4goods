package com.javaforgood.goodandgenerous.data.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;


import com.javaforgood.goodandgenerous.data.model.Journal;
import com.javaforgood.goodandgenerous.data.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, Long> {

	Collection<Journal> findByPublisher(Publisher publisher);

	List<Journal> findByCategoryIdIn(List<Long> ids);

	List<Journal> findByPublishDateBetween(Date startDate, Date endDate);

	List<Journal> findByIsInQueue(boolean isInQueue);

}
