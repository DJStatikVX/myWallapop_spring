package com.uniovi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.ConversationService;
import com.uniovi.services.MessageService;
import com.uniovi.services.OffersService;



@Controller
public class ConversationController {
	
	@Autowired
	private ConversationService conversationService;

	@Autowired
	private OffersService offersService;
	
	@Autowired
	private MessageService msgService;

	@RequestMapping(value = "/conversation/list", method = RequestMethod.GET)
	public String getList(HttpServletRequest request,Model model) {
		User activeUser = (User) request.getSession().getAttribute("activeUser");
		List<Conversation> conversations = conversationService.
				getConversationsForUser(activeUser.getEmail());
		model.addAttribute("conversationsList", conversations);
		return "conversation/list";
	}
	
	@RequestMapping(value = "/conversation/delete/{id}", 
			method = RequestMethod.GET)
	public String getList(HttpServletRequest request,Model model,
			@PathVariable Long id) {
		User activeUser = (User) request.getSession().getAttribute("activeUser");
		Conversation conver = conversationService.getConversation(id);
		if(conver == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"entity not found");
		if(!conver.getBuyer().getEmail().equals(activeUser.getEmail()) &&
				!conver.getOffer().getOwner().getEmail().equals(activeUser.getEmail())){
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		}
		conversationService.deleteConversation(conver);
		List<Conversation> conversations = conversationService.
				getConversationsForUser(activeUser.getEmail());
		model.addAttribute("conversationsList", conversations);
		return "conversation/list";
	}

	@RequestMapping(value = "/conversation/new/{id}", method = RequestMethod.GET)
	public String newConversation(HttpServletRequest request, 
			@PathVariable Long id) {
		
		Offer offer = offersService.getOffer(id);
		
		if (offer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"entity not found");
		}
		
		User owner = offer.getOwner();
		User activeUser = 
				(User) request.getSession().getAttribute("activeUser");
		
		if (offer.isSold() 
				|| offer.getOwner().getEmail().equals(activeUser.getEmail())) {
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		}
		
		Conversation conver = conversationService.getConversation(offer, owner, 
				activeUser);
		
		if (conver != null) {
			return "redirect:/conversation/" + conver.getId(); 
		}
		
		Conversation newConver = 
				conversationService.createConversation(offer, activeUser);
		
		
		return "redirect:/conversation/" + newConver.getId();
	}
	
	@RequestMapping(value = "/conversation/{id}", method = RequestMethod.GET)
	public String getDetails(HttpServletRequest request, 
			@PathVariable Long id, Model model) {
		
		Conversation conversation = conversationService.getConversation(id);
		
		if (conversation == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"entity not found");
		}
		
		User owner = conversation.getOffer().getOwner();
		User buyer = conversation.getBuyer();
		User activeUser = 
				(User) request.getSession().getAttribute("activeUser");
		
		if (!(activeUser.getEmail().equals(owner.getEmail()) 
				|| activeUser.getEmail().equals(buyer.getEmail()))) {
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		}
		
		model.addAttribute("messages", msgService.getByConversationid(id));
		
		model.addAttribute("conversation", conversation);
		
		request.getSession().setAttribute("lastConversation", conversation);
		
		return "conversation/view";
	}
	
	@RequestMapping(value = "/conversation/send", method = RequestMethod.POST)
	public String sendMessage(HttpServletRequest request, Model model) {
		String message = request.getParameter("message");
		
		if (message == null) {
			throw new org.springframework.security.access.AccessDeniedException(
					"403 returned");
		}
		
		Conversation conversation = 
				(Conversation) request.getSession()
					.getAttribute("lastConversation");
		
		User activeUser = 
				(User) request.getSession().getAttribute("activeUser");
		
		conversationService.addMessageToConversation(
				conversation, message, activeUser.getEmail());
		model.addAttribute("conversation", conversation);
		
		return "redirect:/conversation/" + conversation.getId();
	}

}