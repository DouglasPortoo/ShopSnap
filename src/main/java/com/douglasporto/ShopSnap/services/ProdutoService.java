package com.douglasporto.ShopSnap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Categoria;
import com.douglasporto.ShopSnap.domain.Pedido;
import com.douglasporto.ShopSnap.domain.Produto;
import com.douglasporto.ShopSnap.repositories.CategoriaRepository;
import com.douglasporto.ShopSnap.repositories.ProdutoRepository;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository repo;

  @Autowired
  private CategoriaRepository categoriaService;

  public Produto find(Integer id) {
    Optional<Produto> obj = repo.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
  }

  public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    List<Categoria> categorias = categoriaService.findAllById(ids);
    return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
  }

}
