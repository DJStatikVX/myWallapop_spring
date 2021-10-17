package com.uniovi.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.OfferFormValidator;

@Controller
public class OffersController {

	@Autowired
	private OffersService offersService;

	@Autowired
	private OfferFormValidator offerFormValidator;
	
	@Autowired
	private UsersService userService;

	@RequestMapping(value = "/offer/add", method = RequestMethod.GET)
	public String getAddOffer(Model model) {
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String postAddOffer(@Validated Offer offer, BindingResult result, 
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("activeUser");
		offerFormValidator.validate(offer, result);
		System.err.println(offer);
		//eesta comprobacion requiere user
		if(user.getMoney() < 20.0 && offer.isPromoted()) {
			result.rejectValue("promoted", "Error.promoted");
		}
		if (result.hasErrors()) {
			return "offer/add";
		}
		if(offer.isPromoted()) {
			userService.removeMoney(user, 20.0);
		}
		

		offer.setOwner((User) request.getSession().getAttribute("activeUser"));

		offersService.addOffer(offer);
		
		return "redirect:/offer/list";
	}

	@RequestMapping(value = "/offer/sale", method = RequestMethod.GET)
	public String getSale(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("activeUser");
		model.addAttribute("offersList", 
				offersService.getOffersForUser(user.getEmail()));


		return "offer/sale";
	}

	@RequestMapping(value = "/offer/list", method = RequestMethod.GET	)
	public String getList(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText
			, HttpServletRequest request) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if(searchText != null && !searchText.isEmpty()) {
			offers = offersService.getOffersByTitlePage(pageable, searchText);
		}
		else {
			offers = offersService.getOffersPage(pageable);
		}

		//obtenemos refernecia al usuario
		//		para asegurar que no compra sus propios productos
		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/list";
	}

	// solo modifica la lista, no retorna todo el html
	@RequestMapping("/offer/list/update")
	public String updateList(Model model,Pageable pageable){
		Page<Offer> offers = offersService.getOffersPage(pageable);
		model.addAttribute("offersList", offers.getContent());
		return "offer/list :: tableOffers";
	}

	@RequestMapping("/offer/delete/{id}")
	public String delete(Model model, HttpServletRequest request, 
			@PathVariable Long id) {

		Offer offer = offersService.getOffer(id);
		if (offer != null) {
			User owner = offer.getOwner();
			User activeUser = 
					(User) request.getSession().getAttribute("activeUser");
			if (activeUser.getEmail().equals(owner.getEmail())) {
				offersService.deleteOffer(id);
				model.addAttribute("offersList", 
						offersService.getOffersForUser(activeUser.getEmail()));

				return "redirect:/offer/sale";
			}

			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
				"entity not found");
	}

	@RequestMapping("/offer/buy/{id}")
	public String buy(Pageable pageable, HttpServletRequest request, 
			Model model, @PathVariable Long id) {

		Page<Offer> offers = offersService.getOffersPage(pageable);

		User activeUser = 
				(User) request.getSession().getAttribute("activeUser");
		Offer offer = offersService.getOffer(id);

		if (offer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"entity not found");
		}

		if (offer.isSold() || offer.getOwner().getEmail()
				.contentEquals(activeUser.getEmail())) {
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		}



		if (activeUser.getMoney() < offer.getPrice()) {
			model.addAttribute("cantBuy", true);
			model.addAttribute("offersList", offers.getContent());
			model.addAttribute("page", offers);
			return "offer/list";
		}
		offersService.buy(activeUser, offer);
		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("cantBuy", false);
		return "redirect:/offer/list";
	}

	// solo modifica la lista, no retorna todo el html
	@RequestMapping("/offer/bought")
	public String bought(HttpServletRequest request,
			Model model,Pageable pageable){
		User user = (User) request.getSession().getAttribute("activeUser");

		List<Offer> offers = offersService
				.getOffersForBuyer(user.getEmail());
		model.addAttribute("offersList", offers);
		return "offer/bought";
	}

	// solo modifica la lista, no retorna todo el html
	@RequestMapping("/offer/promote/{id}")
	public String bought(HttpServletRequest request,
			Model model,@PathVariable Long id){
		User user = (User) request.getSession().getAttribute("activeUser");
		Offer offer = offersService.getOffer(id);
		
		//casos de error
		if(user == null) 
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");

		if(!offer.getOwner().getEmail().equals(user.getEmail())) 
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");

		if(offer.getBuyer() != null) 
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		
		if(offersService.promote(offer))
			model.addAttribute("error", false);
		else
			model.addAttribute("error", true);
			
		return "redirect:/offer/sale";
	}

	@RequestMapping("/offer/details/{id}")
	public String getDetails(Model model, @PathVariable Long id){
		model.addAttribute("offer", offersService.getOffer(id));
		return "offer/details";
	}

}