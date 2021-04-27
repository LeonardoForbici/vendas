package br.com.examplo.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.examplo.vendas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
