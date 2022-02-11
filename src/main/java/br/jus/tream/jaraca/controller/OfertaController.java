package br.jus.tream.jaraca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {
	
	@GetMapping
	public String getFormularioOferta() {
		
		return "/oferta/index";
	}
}
