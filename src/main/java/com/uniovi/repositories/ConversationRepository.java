package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {
	
	@Query("SELECT distinct c FROM Conversation c WHERE c.offer.owner.email = ?1 "
			+ "OR c.buyer.email = ?1 ORDER BY c.id ASC")
	List<Conversation> findAllByUser(String email);
	
	@Query("SELECT distinct c FROM Conversation c WHERE c.offer.id = ?1")
	List<Conversation> findAllByOffer(Long offerId);
	
	@Query("SELECT distinct c FROM Conversation c WHERE c.offer.id = ?1 "
			+ "AND c.offer.owner.email = ?2 AND c.buyer.email = ?3")
	Conversation findByOfferOwnerAndBuyer(Long offerId, String ownerEmail, 
			String buyerEmail);

}