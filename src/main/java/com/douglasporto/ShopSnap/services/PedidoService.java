package com.douglasporto.ShopSnap.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Pedido;
import com.douglasporto.ShopSnap.repositories.PedidoRepository;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository repo;

  public Pedido buscaDados(Integer id) {
    Optional<Pedido> obj = repo.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
  }
}
