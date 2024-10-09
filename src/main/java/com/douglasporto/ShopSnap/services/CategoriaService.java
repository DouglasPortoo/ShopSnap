package com.douglasporto.ShopSnap.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.douglasporto.ShopSnap.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Categoria;
import com.douglasporto.ShopSnap.repositories.CategoriaRepository;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repo;

  public Categoria find(Integer id) {

    Optional<Categoria> obj = repo.findById(id);

    return obj.orElseThrow(()->new ObjectNotFoundException(
      "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }

  public Categoria insert(Categoria obj){
    obj.setId(null);
    return repo.save(obj);
  }

  public Categoria update(Categoria obj){
    find(obj.getId());
    return repo.save(obj);
  }

  public void delete(Integer id){
    find(id);
    try {
      repo.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
    }
  }
  
}
