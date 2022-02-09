package br.jus.tream.jaraca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.jus.tream.jaraca.dto.RequisicaoNovoPedido;
import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.model.User;
import br.jus.tream.jaraca.repositories.PedidoRepository;
import br.jus.tream.jaraca.repositories.UserRepository;

@Controller
@RequestMapping(value="/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicao) {
        return "pedido/formulario"; 
    }   
	
	@PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
        if (result.hasErrors()) {
        	return "pedido/formulario";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("usuario logado : " + username);
        User user = userRepository.findByUsername(username);
        Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
        pedidoRepository.save(pedido);

        return "redirect:/home";
    }
}
