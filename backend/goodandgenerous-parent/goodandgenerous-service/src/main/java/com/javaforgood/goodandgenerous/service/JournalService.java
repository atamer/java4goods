package com.javaforgood.goodandgenerous.service;

import com.javaforgood.goodandgenerous.data.model.Journal;
import com.javaforgood.goodandgenerous.data.model.Publisher;
import com.javaforgood.goodandgenerous.data.model.User;

import java.util.List;


public interface JournalService {

	List<Journal> listAll(User user);

	List<Journal> publisherList(Publisher publisher);

	Journal publish(Publisher publisher, Journal journal, Long categoryId);

	void unPublish(Publisher publisher, Long journalId);
}
