package com.douglasporto.ShopSnap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.douglasporto.ShopSnap.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Categoria;
import com.douglasporto.ShopSnap.dto.CategoriaDTO;
import com.douglasporto.ShopSnap.repositories.CategoriaRepository;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repo;

  public Categoria find(Integer id) {

    Optional<Categoria> obj = repo.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }

  public List<Categoria> findAll() {
    return repo.findAll();
  }

  public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    return repo.findAll(pageRequest);
  }

  public Categoria insert(Categoria obj) {
    obj.setId(null);
    return repo.save(obj);
  }

  public Categoria update(Categoria obj) {
    var categoria = find(obj.getId());

    if (obj.getNome() != null) {
      categoria.setNome(obj.getNome());
    }

    obj = categoria;

    return repo.save(obj);
  }

  public void delete(Integer id) {
    find(id);
    try {
      repo.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
    }
  }

  public Categoria fromDTO(CategoriaDTO objDTO) {
    return new Categoria(objDTO.id(), objDTO.nome());
  }

}
