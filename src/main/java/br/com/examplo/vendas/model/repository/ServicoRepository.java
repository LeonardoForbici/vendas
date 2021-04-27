package br.com.examplo.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.examplo.vendas.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
