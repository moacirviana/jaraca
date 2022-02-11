package br.jus.tream.jaraca.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.model.StatusPedido;
import br.jus.tream.jaraca.repositories.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRest {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @GetMapping("aguardando")
    public List<Pedido> getPedidosStatusAguardando(){
    	Sort sort = Sort.by("id").ascending();
		PageRequest paginacao = PageRequest.of(0, 5, sort);
		
    	List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
    	return pedidos;
    	
    }
    
    
    
}
