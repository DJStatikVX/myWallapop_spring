package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;

public interface OffersRepository extends CrudRepository<Offer, Long> {
	
	@Query("SELECT o FROM Offer o WHERE o.owner.email = ?1 ORDER BY o.id ASC")
	List<Offer> findAllByUser(String email);
	
	Page<Offer> findAll(Pageable pageable);

	@Query("SELECT o FROM Offer o WHERE LOWER(o.title) LIKE LOWER(?1) ORDER BY o.id ASC")
	Page<Offer> findByTitle(Pageable pageable, String email);

	@Query("SELECT o FROM Offer o WHERE o.buyer.email = ?1 ORDER BY o.id ASC")
	List<Offer> findAllByBuyer(String email);
	
	@Query("SELECT o FROM Offer o WHERE o.promoted = true ORDER BY o.id ASC")
	List<Offer> findAllPromoted();
	
	@Query("SELECT o FROM Offer o ORDER BY o.id ASC")
	List<Offer> findAll();
}