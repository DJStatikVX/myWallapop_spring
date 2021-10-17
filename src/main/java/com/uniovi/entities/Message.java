package com.uniovi.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	
	private String userEmail;
	private String text;
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name="conversation_id")
	Conversation conversation;
	
	
	
	 Message() {
		super();
	}

	public Message(String text, String email, Conversation conver) {
		super();
		this.text = text;
		this.userEmail = email;
		this.date = LocalDateTime.now();
		this.conversation = conver;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public Long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getDate() {
		return date;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", userEmail=" + userEmail + ", text=" + text + ", date=" + date + "]";
	}
	
	
	
}
