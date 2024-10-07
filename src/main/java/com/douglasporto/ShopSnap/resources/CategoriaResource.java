package com.douglasporto.ShopSnap.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasporto.ShopSnap.domain.Categoria;
import com.douglasporto.ShopSnap.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> find(@PathVariable Integer id) {

    Categoria obj = service.buscaDados(id);
    return ResponseEntity.ok().body(obj);
  }
}
