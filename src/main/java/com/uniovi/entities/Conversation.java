package com.uniovi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "conversation")
public class Conversation {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL,
	        orphanRemoval = true, mappedBy="conversation")
	private List<Message> messages;
	
	
//	@ManyToOne
//	@JoinColumn(name = "seller_id")
//	private User owner;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyer;
	
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	
	public Conversation() {
		
	}
	
	

	public Conversation(Offer offer, User buyer ) {
		super();
		this.messages = new ArrayList<Message>();
//		this.owner = offer.getOwner();
		this.buyer = buyer;
		this.offer = offer;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

//	public User getOwner() {
//		return owner;
//	}
//
//	public void setOwner(User owner) {
//		this.owner = owner;
//	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Override
	public String toString() {
		return "Conversation [messages=" + messages.size() 
				+ ", buyer=" + buyer + ", offer=" + offer + "]";
	}
	
	public Message addMessage(String text, String senderEmail) {
		Message msg = new Message(text, senderEmail, this);
		
		this.messages.add(msg);
		return msg;
		
	}

	
}