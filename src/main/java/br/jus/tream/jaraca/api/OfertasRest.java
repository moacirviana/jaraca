package br.jus.tream.jaraca.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tream.jaraca.dto.RequisicaoNovaOferta;
import br.jus.tream.jaraca.model.Oferta;
import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.repositories.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		
		Optional<Pedido> pedidoFind = pedidoRepository.findById(requisicao.getPedidoId());
		if (!pedidoFind.isPresent()) {
			return null;
		}
		Pedido pedido = pedidoFind.get();
		Oferta oferta = requisicao.toOferta();
		oferta.setPedido(pedido);
		pedido.getOfertas().add(oferta);
		// com isso salva a oferta no banco
		pedidoRepository.save(pedido);
		
		return oferta;
		
	}
}
