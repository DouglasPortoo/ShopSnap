package com.douglasporto.ShopSnap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Categoria;
import com.douglasporto.ShopSnap.repositories.CategoriaRepository;


@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repo;

  public Categoria buscaDados(Integer id) {
    Categoria obj = repo.findById(id).orElse(null);

    return obj;
  }
  
}
