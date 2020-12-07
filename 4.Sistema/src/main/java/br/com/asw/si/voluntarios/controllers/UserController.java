package br.com.asw.si.voluntarios.controllers;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.asw.si.voluntarios.models.User;
import br.com.asw.si.voluntarios.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

    @GetMapping("/userName")
    @ResponseBody
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    	if(principal == null) {
    		return null;
    	}
        return Collections.singletonMap("name", principal.getAttribute("login"));
    }
    
    @GetMapping("/signup")
    public String register(User user) {
    	return "userForm";
    }

    @PostMapping
    public String create(@Valid User user, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
			return "/userForm";
		}
    	
    	if(service.create(user) != null) {
    		return "redirect:/login";
    	} else {
    		model.addAttribute("msg", "Usuário ou e-mail já está em uso!");
    		return "/userForm";
    	}
    }
}
