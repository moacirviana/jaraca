package br.jus.tream.jaraca.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.model.StatusPedido;
import br.jus.tream.jaraca.repositories.PedidoRepository;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {
    
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/pedidos")
    public ModelAndView home(Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
        ModelAndView mv = new ModelAndView("usuario/index");
        mv.addObject("pedidos", pedidos);
        return mv; 
    }   
	
	@GetMapping("/pedidos/{status}")
	public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUsuario(
				        			StatusPedido.valueOf(status.toUpperCase()),principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/index"; 
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario";
	}
	
}
