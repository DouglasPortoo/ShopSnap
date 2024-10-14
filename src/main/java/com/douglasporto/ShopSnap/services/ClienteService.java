package com.douglasporto.ShopSnap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Cidade;
import com.douglasporto.ShopSnap.domain.Cliente;
import com.douglasporto.ShopSnap.domain.Endereco;
import com.douglasporto.ShopSnap.domain.enums.TipoCliente;
import com.douglasporto.ShopSnap.dto.ClienteDTO;
import com.douglasporto.ShopSnap.dto.ClienteNewDTO;
import com.douglasporto.ShopSnap.repositories.ClienteRepository;
import com.douglasporto.ShopSnap.repositories.EnderecoRepository;
import com.douglasporto.ShopSnap.services.exceptions.DataIntegrityException;
import com.douglasporto.ShopSnap.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repo;

  @Autowired
	private EnderecoRepository enderecoRepository;

  @Autowired
  private BCryptPasswordEncoder pe;

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

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
      throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionadas");
    }
  }

    public Cliente fromDTO(ClienteDTO objDTO) {
    return new Cliente(objDTO.id(), objDTO.nome(), objDTO.email(), null, null,null);
  }

  public Cliente fromDTO(ClienteNewDTO objDTO) {
    Cliente cli = new Cliente(null, objDTO.nome(), objDTO.email(), objDTO.cpfOuCnpj(), TipoCliente.toEnum(objDTO.tipo()), pe.encode(objDTO.senha()));

    Cidade cid = new Cidade(objDTO.cidadeId(), null, null);

    Endereco end = new Endereco(null, objDTO.logradouro(), objDTO.numero(), objDTO.complemento(), objDTO.bairro(), objDTO.cep(), cli,cid);

    cli.getEnderecos().add(end);
    cli.getTelefones().add(objDTO.telefone1());

    if (objDTO.telefone2()!=null) {
			cli.getTelefones().add(objDTO.telefone2());
		}
		if (objDTO.telefone3()!=null) {
			cli.getTelefones().add(objDTO.telefone3());
		}

    return cli;
  }

}
