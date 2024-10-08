package com.douglasporto.ShopSnap.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasporto.ShopSnap.domain.Cliente;
import com.douglasporto.ShopSnap.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> find(@PathVariable Integer id){

    Cliente obj = service.buscaDados(id);
    return ResponseEntity.ok().body(obj);
  }
  
  
}
