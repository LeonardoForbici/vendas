package br.com.examplo.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.examplo.vendas.model.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

}
