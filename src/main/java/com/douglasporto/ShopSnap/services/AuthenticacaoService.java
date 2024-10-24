package com.douglasporto.ShopSnap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.repositories.ClienteRepository;

@Service
public class AuthenticacaoService implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserDetails usuario = clienteRepository.findByEmail(username);

    if (usuario == null ){
      throw new UsernameNotFoundException("Dados inválidos");
    }

    return usuario;
  }
  
}
