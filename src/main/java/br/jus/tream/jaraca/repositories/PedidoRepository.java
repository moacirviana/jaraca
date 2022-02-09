package br.jus.tream.jaraca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByStatus(StatusPedido aguardando);
	
}
