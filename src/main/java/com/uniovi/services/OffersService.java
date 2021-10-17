package com.uniovi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class OffersService {
	
	@Autowired
	private OffersRepository offersRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private ConversationRepository conversRepository;
	
	public Offer getOffer(Long id) {
		Optional<Offer> oOffer = offersRepository.findById(id);
		return oOffer.isPresent() ? oOffer.get() : null;
	}
	
	public List<Offer> getOffers() {
		List<Offer> offers = offersRepository.findAll();
		return offers;
	}
	
	public Page<Offer> getOffersPage(Pageable pageable) {
		Page<Offer> offers = offersRepository.findAll(pageable);
		return offers;
	}
	
	public Page<Offer> getOffersByTitlePage(Pageable pageable, String title) {
		Page<Offer> offers = offersRepository.findByTitle(pageable, "%" +title + "%");
		return offers;
	}
	
	public void addOffer(Offer offer) {
		offersRepository.save(offer);
	}
	
	public void deleteOffer(Long id) {
		List<Conversation> convers = conversRepository.findAllByOffer(id);
		conversRepository.deleteAll(convers);
		offersRepository.deleteById(id);
	}
	
	public List<Offer> getOffersForUser(String email) {
		return offersRepository.findAllByUser(email);
	}

	public List<Offer> getOffersForBuyer(String email) {
		return offersRepository.findAllByBuyer(email);
	}
	
	public void buy(User user, Offer offer) {
		user.setMoney(user.getMoney() - offer.getPrice());
		user.getBuyedOffers().add(offer);
		offer.setBuyer(user);
		offer.setSold(true);
		offer.getOwner().setMoney(
				offer.getOwner().getMoney() + offer.getPrice());
		usersRepository.save(user);
		offersRepository.save(offer);
	}

	public boolean promote(Offer offer) {
		double money = offer.getOwner().getMoney();
		if( money < 20) {
			return false;
		}
		offer.getOwner().setMoney(money - 20);
		offer.setPromoted(true);	
		offersRepository.save(offer);
		return true;
	}

	public List<Offer> getPromoted() {
		return offersRepository.findAllPromoted();
	}
	
}