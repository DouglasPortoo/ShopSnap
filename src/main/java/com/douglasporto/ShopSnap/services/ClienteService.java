package com.douglasporto.ShopSnap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Cliente;
import com.douglasporto.ShopSnap.dto.ClienteDTO;
import com.douglasporto.ShopSnap.repositories.ClienteRepository;
import com.douglasporto.ShopSnap.services.exceptions.DataIntegrityException;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repo;

  public Cliente find(Integer id) {
    Optional<Cliente> obj = repo.findById(id);

    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }

  public List<Cliente> findAll() {
    return repo.findAll();
  }

  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    return repo.findAll(pageRequest);
  }

  public Cliente insert(Cliente obj) {
    obj.setId(null);
    return repo.save(obj);
  }

  public Cliente update(Cliente obj) {
    var cliente = find(obj.getId());

    if (obj.getNome() != null) {
      cliente.setNome(obj.getNome());
    }
    if (obj.getEmail() != null) {
      cliente.setEmail(obj.getEmail());
    }
    if (obj.getCpfOuCnpj() != null) {
      cliente.setCpfOuCnpj(obj.getCpfOuCnpj());
    }
    if (obj.getTipo() != null) {
      cliente.setTipo(obj.getTipo());
    }

    obj = cliente;

    return repo.save(obj);
  }

  public void delete(Integer id) {
    find(id);
    try {
      repo.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma cliente que possui produtos");
    }
  }

    public Cliente fromDTO(ClienteDTO objDTO) {
    return new Cliente(objDTO.id(), objDTO.nome(), objDTO.email(), null, null);
  }

}
