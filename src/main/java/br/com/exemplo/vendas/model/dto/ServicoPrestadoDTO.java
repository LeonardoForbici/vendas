package br.com.exemplo.vendas.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
	
	@NotNull(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@NotNull(message = "{campo.preco.obrigatorio}")
	private String preco;
	
	@NotNull(message = "{campo.data.obrigatorio}")
	private String data;

	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
	
}
