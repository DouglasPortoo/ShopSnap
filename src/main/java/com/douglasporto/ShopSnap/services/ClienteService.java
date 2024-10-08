package com.douglasporto.ShopSnap.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Cliente;
import com.douglasporto.ShopSnap.repositories.ClienteRepository;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repo;

  public Cliente buscaDados(Integer id) {
    Optional<Cliente> obj = repo.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }
}
