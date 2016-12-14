package com.javaforgood.goodandgenerous.service.jobs;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.javaforgood.goodandgenerous.data.model.EventQueue;
import com.javaforgood.goodandgenerous.data.model.EventType;
import com.javaforgood.goodandgenerous.data.model.JobStatus;
import com.javaforgood.goodandgenerous.data.model.Journal;
import com.javaforgood.goodandgenerous.data.repository.EventQueueRepository;
import com.javaforgood.goodandgenerous.data.repository.JournalRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EventJobImpl implements EventJob {

	@Autowired
	private JournalRepository journalRepository;

	@Autowired
	private EventQueueRepository eventQueueRepository;
	private static final Logger logger = Logger.getLogger(EventJobImpl.class);

	@Override
	//@Scheduled(fixedDelay = 3000)
	public void generateImmediatelyEvents() {
		List<Journal> notInQueueJournalList = journalRepository.findByIsInQueue(false);
		notInQueueJournalList.forEach(item -> {
			try {
				transactionalUpdate(item);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		});
		checkDayTurn();

	}

	// work once on 10 minutes and check daily event
	@Override
//	@Scheduled(fixedDelay = 1000 * 60 * 10, initialDelay = 0)
	public void generateDailyEvent() {
		checkDayTurn();
	}

	private void checkDayTurn() {
		try {
			LocalDate now = LocalDate.now();
			Optional<EventQueue> optionalEventQueue = eventQueueRepository.findByDayTurn(now.toString());
			if (!optionalEventQueue.isPresent()) {
				EventQueue event = new EventQueue();
				event.setEventType(EventType.DAILY);
				event.setDayTurn(now.toString());
				event.setStatus(JobStatus.AVAILABLE);
				eventQueueRepository.save(event);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	// make method isolated and consistent
	@Transactional(value = TxType.REQUIRES_NEW)
	private void transactionalUpdate(Journal item) {
		EventQueue event = new EventQueue();
		event.setEventType(EventType.IMMEDIATELY);
		event.setJournal(item);
		event.setStatus(JobStatus.AVAILABLE);
		eventQueueRepository.save(event);
		item.setInQueue(true);
		journalRepository.save(item);

	}
}
