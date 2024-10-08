package com.douglasporto.ShopSnap.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import com.douglasporto.ShopSnap.domain.Pedido;
import com.douglasporto.ShopSnap.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  @Autowired
  private PedidoService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Pedido> find(@PathVariable Integer id) {

    Pedido obj = service.find(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody Pedido obj){
    obj = service.insert(obj);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(obj.getId()).toUri();

  return ResponseEntity.created(uri).build();

  }

}
