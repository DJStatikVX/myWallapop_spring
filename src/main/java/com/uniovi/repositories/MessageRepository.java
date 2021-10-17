package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
	

	
	@Query("SELECT m from Message m where m.conversation.id = ?1")
	List<Message> findByConversationId(Long converId);

}