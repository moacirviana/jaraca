package br.jus.tream.jaraca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.model.StatusPedido;
import br.jus.tream.jaraca.repositories.PedidoRepository;

@Controller
@RequestMapping(value="/home")
public class HomeController {
    
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
    public ModelAndView home() {
		Sort sort = Sort.by("dataDaEntrega").ascending();
		PageRequest paginacao = PageRequest.of(0, 5, sort);
		
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("pedidos", pedidos);
        return mv; 
    }   
	
}
