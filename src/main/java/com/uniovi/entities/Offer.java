package com.uniovi.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	private String details;
	private LocalDate registerDate;
	private double price;
	private boolean sold;
	private boolean promoted;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyer;
	
	public Offer() {
		this.registerDate = LocalDate.now();		
	}

	public Offer(String title, String details, double price, User owner) {
		
		super();
		this.title = title;
		this.details = details;
		this.registerDate = LocalDate.now();
		this.price = price;
		this.owner = owner;
		this.sold = false;
		this.promoted = false;
	}
	


	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", details=" + details +
				", registerDate=" + registerDate
				+ ", price=" + price + ", sold=" + sold + ", owner=" 
				+ " promoted= " + promoted ;
	}

	public void setBuyer(User user) {
		buyer = user;
	}
	
	public User getBuyer() {
		return buyer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public boolean isPromoted() {
		return promoted;
	}

	public void setPromoted(boolean promoted) {
		this.promoted = promoted;
	}
	
}