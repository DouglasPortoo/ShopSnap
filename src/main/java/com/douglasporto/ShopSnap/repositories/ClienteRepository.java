package com.douglasporto.ShopSnap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglasporto.ShopSnap.domain.Cliente;

import org.springframework.transaction.annotation.Transactional;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
  
  @Transactional(readOnly = true)
  Cliente findByEmail(String email);
  
}
