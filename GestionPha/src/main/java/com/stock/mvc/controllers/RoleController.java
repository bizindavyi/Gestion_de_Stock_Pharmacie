package com.stock.mvc.controllers;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stock.mvc.entites.Article;

import com.stock.mvc.entites.Roles;
import com.stock.mvc.entites.Utilisateur;

import com.stock.mvc.services.IRolesService;
import com.stock.mvc.services.IUtilisateurService;


@Controller
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private IRolesService rolesService;
	
	@Autowired
	private IUtilisateurService utilisateurService;
	

	
	@RequestMapping(value = "/")
	public String role(Model model) {
		List<Roles> roles = rolesService.selectAll();
		if (roles == null) {
			roles = new ArrayList<Roles>();
		}
		model.addAttribute("roles", roles);
		return "role/role";
	}
	
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterRole(Model model) {
		Article role = new Article();
		List<Utilisateur> utilisateur = utilisateurService.selectAll();
		if (utilisateur == null) {
			utilisateur = new ArrayList<Utilisateur>();
		}
		model.addAttribute("role", role);
		model.addAttribute("utilisateur", utilisateur);
		return "role/ajouterRole";
	}
	
	@RequestMapping(value = "/enregistrer")
	public String enregistrerRole(Model model, Roles role) {
		
		if (role != null) {
			if (role.getIdRole() != null) {
				rolesService.update(role);
			} else {
				rolesService.save(role);
			}
		}
		return "redirect:/role/";
	}
	
	@RequestMapping(value = "/modifier/{idRole}")
	public String modifierRole(Model model, @PathVariable Long idRole) {
		if (idRole != null) {
			Roles role = rolesService.getById(idRole);
			List<Utilisateur> utilisateur = utilisateurService.selectAll();
			if (utilisateur == null) {
				utilisateur = new ArrayList<Utilisateur>();
			}
			model.addAttribute("utilisateur", utilisateur);
			if (role != null) {
				model.addAttribute("role", role);
			}
		}
		return "article/ajouterRole";
	}
	
	@RequestMapping(value = "/supprimer/{idRole}")
	public String supprimerRole(Model model, @PathVariable Long idRole) {
		if (idRole != null) {
			Roles Roles = rolesService.getById(idRole);
			if (Roles != null) {
				//TODO Verification avant suppression
				rolesService.remove(idRole);
			}
		}
		return "redirect:/role/";
	}
	
	
}
