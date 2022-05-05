package br.com.exemplo.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.vendas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
}
