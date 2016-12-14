package com.javaforgood.goodandgenerous.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;


import com.javaforgood.goodandgenerous.data.model.*;
import com.javaforgood.goodandgenerous.data.repository.*;
import com.javaforgood.goodandgenerous.service.JournalService;
import com.javaforgood.goodandgenerous.service.ServiceException;
import com.javaforgood.goodandgenerous.service.UserService;
import com.javaforgood.goodandgenerous.service.jobs.EventJob;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javaforgood.goodandgenerous.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobsTest {

	@Autowired
	private UserService userService;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private JournalService journalService;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private EventQueueRepository eventQueueRepository;
	
	@Autowired
	private ImmediatelyJobLogRepository immediatelyJobRepository;
	
	

	
	@Autowired
	private DailyJobLogRepository dailyRepository;
	
	@Autowired
	private EventJob eventJob;
	
	
	

	protected User getUser(String name) {
		Optional<User> user = userService.getUserByLoginName(name);
		if (!user.isPresent()) {
			fail("user1 doesn't exist");
		}
		return user.get();
	}

	
	public Category getCategory() {

		List<Category> categoryList = categoryRepository.findAll();

		assertNotNull(categoryList);
		return categoryList.get(0);

	}

	
	public void subscribeSuccess(Category category, User user) {		
		try {
			userService.subscribe(user, category.getId());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}

	}

	
	public void publishJournalSuccess(User user,Category category) {
				
		
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName("SOME_NAME");
		journal.setUuid("SOME_EXTERNAL_ID");
		journal.setCategory(category);
		try {
			journalService.publish(p.get(), journal, category.getId());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}

	}

	@Test()
	public void eventJobSuccess() {
		User user = getUser("publisher2");
		Category category = getCategory();
		
		try {
			immediatelyJobRepository.deleteAll();
			dailyRepository.deleteAll();
			eventQueueRepository.deleteAll();
			
			subscribeSuccess(category, user);
			publishJournalSuccess(user, category);
			
			eventJob.generateImmediatelyEvents();
			List<EventQueue> eventQueueList = eventQueueRepository.findByEventTypeAndStatus(EventType.IMMEDIATELY, JobStatus.AVAILABLE);
			
			assertEquals(1, eventQueueList.size());
			
			eventJob.generateDailyEvent();
			List<EventQueue> dailyEventQueueList = eventQueueRepository.findByEventTypeAndStatus(EventType.DAILY, JobStatus.AVAILABLE);
			assertEquals(1, dailyEventQueueList.size());
			
										
		} catch (ServiceException e ) {
			fail(e.getMessage());
		}

	}
	
	

}
