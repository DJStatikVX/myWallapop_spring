package com.uniovi.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private OffersService offersService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);		
		if(result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("errorMessage", "Las credenciales no coinciden.");
		return "login";
	}

	@RequestMapping("/user/list" )
	public String getListado(Model model, @RequestParam(value = "", required = false) String searchText){


		List<User> users;
		//Page<User> users;		
		if(searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUserByNameLastnameAndEmail(searchText);
		}
		else {
			users = usersService.getUsers();
		}

		model.addAttribute("usersList", users);		
		//añadimos la lista de ids que borrar
		model.addAttribute("deleteList", new ArrayList<Long>());
		return "user/list";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String delete(@RequestParam List<Long> deleteList) {
		for (Long id : deleteList) {
			usersService.deleteUser(id);
		}
		
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		List<Offer> offers = offersService.getPromoted();
		model.addAttribute("offersList", offers);
		return "home";
	}
	
}