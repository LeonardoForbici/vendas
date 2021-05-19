package br.com.examplo.vendas.model.dto;

import br.com.examplo.vendas.model.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
	private String descricao;
	private String preco;
	private String data;
	private Integer idCliente;
	private Cliente cliente;
}
