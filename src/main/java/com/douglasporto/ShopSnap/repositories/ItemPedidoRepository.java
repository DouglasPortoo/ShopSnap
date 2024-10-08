package com.douglasporto.ShopSnap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglasporto.ShopSnap.domain.ItemPedido;
import com.douglasporto.ShopSnap.domain.ItemPedidoPK;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
  
}
