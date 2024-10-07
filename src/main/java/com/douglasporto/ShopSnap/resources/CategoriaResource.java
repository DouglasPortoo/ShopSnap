package com.douglasporto.ShopSnap.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
  
  @GetMapping
  public String getMethodName() {
      return "REST está funcionando!";
  }
}
