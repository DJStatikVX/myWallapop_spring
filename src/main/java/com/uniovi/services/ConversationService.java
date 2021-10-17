package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;
import com.uniovi.repositories.MessageRepository;

@Service
public class ConversationService {
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private MessageRepository msgRepo;
	
	
	public Conversation getConversation(Long id) {
		Optional<Conversation> conver = conversationRepository.findById(id);
		return conver.isPresent() ? conver.get() : null;
	}
	
	public List<Conversation> getConversations() {
		List<Conversation> conver = new ArrayList<Conversation>(); 
		conversationRepository.findAll().forEach(conver :: add);
		return conver;
	}
	
	public List<Conversation> getConversationsForUser(String email) {
		return conversationRepository.findAllByUser(email);
	}
	
	public void deleteConversation(Conversation conver) {
		conversationRepository.delete(conver);
	}

	public Conversation getConversation(Offer offer, User owner, User buyer) {
		return conversationRepository.findByOfferOwnerAndBuyer(
				offer.getId(), owner.getEmail(), buyer.getEmail());
	}
	
	public Conversation createConversation(Offer offer, User buyer) {
		Conversation conversation = new Conversation(offer, buyer);
		conversationRepository.save(conversation);
		return conversation;
	}
	
	public void addMessageToConversation(Conversation conversation, 
			String text, String userEmail) {
		
		Message msg = conversation.addMessage(text, userEmail);
		msgRepo.save(msg);
		System.err.println(msg);
		conversationRepository.save(conversation);
	}

}