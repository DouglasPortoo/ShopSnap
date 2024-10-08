package com.douglasporto.ShopSnap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglasporto.ShopSnap.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
  
}
