package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
import com.uniovi.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository msgRepo;
	
	public List<Message> getByConversationid(Long id) {
		return msgRepo.findByConversationId(id);
	}

}