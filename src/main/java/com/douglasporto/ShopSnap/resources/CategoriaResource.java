package com.douglasporto.ShopSnap.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglasporto.ShopSnap.domain.Categoria;
import com.douglasporto.ShopSnap.dto.CategoriaDTO;
import com.douglasporto.ShopSnap.services.CategoriaService;

import java.net.URI;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Categoria> find(@PathVariable Integer id) {

    Categoria obj = service.find(id);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<List<CategoriaDTO>> findAll(){
    List<Categoria> categorias = service.findAll();
    List<CategoriaDTO> categoriasDTO = categorias.stream().map(obj -> new CategoriaDTO(obj)).toList();
    return ResponseEntity.ok().body(categoriasDTO);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<CategoriaDTO>> findPage(
    @RequestParam(value = "page", defaultValue = "0") Integer page, 
    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
    @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
    @RequestParam(value = "direction", defaultValue = "ASC")String direction){

    Page<Categoria> categorias = service.findPage(page, linesPerPage, orderBy, direction);
    Page<CategoriaDTO> categoriasDTO = categorias.map(obj -> new CategoriaDTO(obj));
    return ResponseEntity.ok().body(categoriasDTO);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@Validated @RequestBody CategoriaDTO objDTO){
    Categoria obj = service.fromDTO(objDTO);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@Validated @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
    Categoria obj = service.fromDTO(objDTO);
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();

  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
