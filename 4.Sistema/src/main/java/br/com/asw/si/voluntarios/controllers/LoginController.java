package br.com.asw.si.voluntarios.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping(value="/login")
	public String login() {
		return "/login";
	}
}
