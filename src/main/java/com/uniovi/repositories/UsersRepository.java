package com.uniovi.repositories;
import com.uniovi.entities.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	
	@Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE LOWER(?1) OR "
			+ "LOWER (u.lastName) LIKE LOWER(?1))")
	Page<User> searchByNameAndLastname(Pageable pageable, String searchtext);

	@Query("SELECT u FROM User u WHERE (NOT(u.role = 'ROLE_ADMIN') AND "
			+ "(LOWER(u.name) LIKE LOWER(?1) OR "
			+ "LOWER (u.lastName) LIKE LOWER(?1) OR "
			+ "LOWER (u.email) LIKE LOWER(?1)))")
	List<User> searchByNameLastnameAndEmail(String searchText);
}