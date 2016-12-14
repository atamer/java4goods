package com.javaforgood.goodandgenerous.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.List;
import java.util.Optional;

import com.javaforgood.goodandgenerous.Application;

import com.javaforgood.goodandgenerous.data.model.Journal;
import com.javaforgood.goodandgenerous.data.model.Publisher;
import com.javaforgood.goodandgenerous.data.model.User;
import com.javaforgood.goodandgenerous.data.repository.PublisherRepository;
import com.javaforgood.goodandgenerous.service.JournalService;
import com.javaforgood.goodandgenerous.service.ServiceException;
import com.javaforgood.goodandgenerous.service.UserService;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JournalServiceTest {

	private final static String NEW_JOURNAL_NAME = "New Journal";

	
	private MockMvc mockMvc;

	
	@Autowired
	private JournalService journalService;

	@Autowired
	private UserService userService;

	@Autowired
	private PublisherRepository publisherRepository;

	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		
	}

	
	@Test
	public void browseSubscribedUser() {
		List<Journal> journals = journalService.listAll(getUser("user1"));
		assertNotNull(journals);
		assertEquals(1, journals.size());

		assertEquals(new Long(1), journals.get(0).getId());
		assertEquals("Medicine", journals.get(0).getName());
		assertEquals(new Long(1), journals.get(0).getPublisher().getId());
		assertNotNull(journals.get(0).getPublishDate());
	}

	@Test
	public void browseUnSubscribedUser() {
		List<Journal> journals = journalService.listAll(getUser("user2"));
		assertEquals(0, journals.size());
	}

	@Test
	public void listPublisher() {
		User user = getUser("publisher1");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		List<Journal> journals = journalService.publisherList(p.get());
		assertEquals(2, journals.size());

		assertEquals(new Long(1), journals.get(0).getId());
		assertEquals(new Long(2), journals.get(1).getId());

		assertEquals("Medicine", journals.get(0).getName());
		assertEquals("Test Journal", journals.get(1).getName());
		journals.stream().forEach(j -> assertNotNull(j.getPublishDate()));
		journals.stream().forEach(j -> assertEquals(new Long(1), j.getPublisher().getId()));

	}

	@Test(expected = ServiceException.class)
	public void publishFail() throws ServiceException {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName("New Journal");

		journalService.publish(p.get(), journal, 1L);
	}

	@Test(expected = ServiceException.class)
	public void publishFail2() throws ServiceException {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName("New Journal");

		journalService.publish(p.get(), journal, 150L);
	}

	@Test()
	public void publishSuccess() {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName(NEW_JOURNAL_NAME);
		journal.setUuid("SOME_EXTERNAL_ID");
		try {
			journalService.publish(p.get(), journal, 3L);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}

		List<Journal> journals = journalService.listAll(getUser("user1"));
		assertEquals(2, journals.size());

		journals = journalService.publisherList(p.get());
		assertEquals(2, journals.size());
		assertEquals(new Long(3), journals.get(0).getId());
		assertEquals(new Long(4), journals.get(1).getId());
		assertEquals("Health", journals.get(0).getName());
		assertEquals(NEW_JOURNAL_NAME, journals.get(1).getName());
		journals.stream().forEach(j -> assertNotNull(j.getPublishDate()));
		journals.stream().forEach(j -> assertEquals(new Long(2), j.getPublisher().getId()));
	}

	@Test(expected = ServiceException.class)
	public void unPublishFail() {
		User user = getUser("publisher1");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		journalService.unPublish(p.get(), 4L);
	}

	@Test(expected = ServiceException.class)
	public void unPublishFail2() {
		User user = getUser("publisher1");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		journalService.unPublish(p.get(), 100L);
	}

	@Test
	public void unPublishSuccess() {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		journalService.unPublish(p.get(), 4L);

		List<Journal> journals = journalService.publisherList(p.get());
		assertEquals(1, journals.size());
		journals = journalService.listAll(getUser("user1"));
		assertEquals(1, journals.size());
	}
	
	
	
	

	protected User getUser(String name) {
		Optional<User> user = userService.getUserByLoginName(name);
		if (!user.isPresent()) {
			fail("user1 doesn't exist");
		}
		return user.get();
	}

}
