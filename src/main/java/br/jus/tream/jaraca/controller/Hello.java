package br.jus.tream.jaraca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {
	@GetMapping(value = "/hello")
	public String hello(Model model) {
		model.addAttribute("nome", "Spring MVC");
		return "hello";
	}
}
