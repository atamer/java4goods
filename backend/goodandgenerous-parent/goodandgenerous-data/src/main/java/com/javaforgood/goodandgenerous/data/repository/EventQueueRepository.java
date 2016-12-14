package com.javaforgood.goodandgenerous.data.repository;

import java.util.List;
import java.util.Optional;

import com.javaforgood.goodandgenerous.data.model.EventQueue;
import com.javaforgood.goodandgenerous.data.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javaforgood.goodandgenerous.data.model.JobStatus;

public interface EventQueueRepository extends JpaRepository<EventQueue, Long> {

	List<EventQueue> findByEventTypeAndStatus(EventType eventType, JobStatus jobStatus);

	List<EventQueue> findByEventTypeAndStatusAndDayTurn(EventType eventType, JobStatus jobStatus, String dayTurn);

	Optional<EventQueue> findByDayTurn(String dayTurn);

}
