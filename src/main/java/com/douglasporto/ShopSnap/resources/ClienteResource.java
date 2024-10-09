package com.douglasporto.ShopSnap.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglasporto.ShopSnap.domain.Cliente;
import com.douglasporto.ShopSnap.dto.ClienteDTO;
import com.douglasporto.ShopSnap.services.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> find(@PathVariable Integer id) {

    Cliente obj = service.find(id);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> findAll() {
    List<Cliente> clientes = service.findAll();
    List<ClienteDTO> clientesDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).toList();
    return ResponseEntity.ok().body(clientesDTO);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<ClienteDTO>> findPage(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

    Page<Cliente> clientes = service.findPage(page, linesPerPage, orderBy, direction);
    Page<ClienteDTO> clientesDTO = clientes.map(obj -> new ClienteDTO(obj));
    return ResponseEntity.ok().body(clientesDTO);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@Validated @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
    Cliente obj = service.fromDTO(objDTO);
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();

  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
