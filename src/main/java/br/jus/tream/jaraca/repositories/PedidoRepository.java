package br.jus.tream.jaraca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jus.tream.jaraca.model.Pedido;
import br.jus.tream.jaraca.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByStatus(StatusPedido aguardando);
	
	@Query("select p from Pedido p join p.user u where u.username=:username")
	List<Pedido> findAllByUsuario(@Param("username") String username);
	
}
