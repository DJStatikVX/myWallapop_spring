package com.uniovi.services;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;
@Service
public class UsersService {


	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private OffersRepository offersRepository;
	
	@Autowired
	private ConversationRepository conversRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		
		for(User user : users) {
			if(user.getRole().equals("ROLE_ADMIN")) {
				users.remove(user);
				break;
			}
		}
		
		return users;
	}

	public User getUser(Long id) {
		return usersRepository. findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void editUser(User user) {
		usersRepository.save(user);

	}

	public void deleteUser(Long id) {
		User user = usersRepository.findById(id).get();
		List<Conversation> convers = 
				conversRepository.findAllByUser(user.getEmail());
		conversRepository.deleteAll(convers);
		List<Offer> offers = offersRepository.findAllByUser(user.getEmail());
		offersRepository.deleteAll(offers);
		List<Offer> boughtOffers = offersRepository
				.findAllByBuyer(user.getEmail());
		for (Offer o : boughtOffers) {
			o.setBuyer(null);
		}
		offersRepository.saveAll(boughtOffers);
		usersRepository.deleteById (id);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}


	public Page<User> searchUserByNameAndLastname(Pageable pageable,String searchText){
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.searchByNameAndLastname(pageable,searchText);
		return users;
	}

	public List<User> searchUserByNameLastnameAndEmail(String searchText) {
		List<User> users = new ArrayList<User>();
		searchText = "%"+searchText+"%";
		users = usersRepository.searchByNameLastnameAndEmail(searchText);
		return users;
	}

	public void removeMoney(User user, double d) {
		user.setMoney(user.getMoney() - d);
		usersRepository.save(user);
		
	}




}
