package br.com.examplo.vendas.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	@NotEmpty
	private String nome;

	@Column(nullable = false, length = 11)
	@NotNull
	@CPF
	private String cpf;

	@Column(name = "data_gravacao", updatable = false)
	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate dataGravacao;

}
