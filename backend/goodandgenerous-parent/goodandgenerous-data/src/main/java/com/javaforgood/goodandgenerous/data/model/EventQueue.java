package com.javaforgood.goodandgenerous.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event_queue")
public class EventQueue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne(optional = true)
	private Journal journal;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private JobStatus status;

	@Column(name = "event_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private EventType eventType;

	@Column(name = "day_turn")
	private String dayTurn;

	public Long getId() {
		return id;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getDayTurn() {
		return dayTurn;
	}

	public void setDayTurn(String dayTurn) {
		this.dayTurn = dayTurn;
	}

}
