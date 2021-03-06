package br.com.exemplo.vendas.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.exemplo.vendas.model.Cliente;
import br.com.exemplo.vendas.model.ServicoPrestado;
import br.com.exemplo.vendas.model.dto.ServicoPrestadoDTO;
import br.com.exemplo.vendas.model.repository.ClienteRepository;
import br.com.exemplo.vendas.model.repository.ServicoPrestadoRepository;
import br.com.exemplo.vendas.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository servicoPrestadorepository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		Integer idCliente = dto.getIdCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		return servicoPrestadorepository.save(servicoPrestado);
	}

	@GetMapping
	public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {

		boolean nomeIndefinido = false;

		if (nome.equals("undefined")) {
			nomeIndefinido = true;
		}

		if ((!nomeIndefinido || nome.isEmpty()) && mes != null) {
			return servicoPrestadorepository.findByNomeClienteAndMes("%" + nome + "%", mes);
		}else if(mes != null) {
			return servicoPrestadorepository.findByMes(mes);
		}else if((!nomeIndefinido && !nome.isEmpty())) {
			return servicoPrestadorepository.findByNome(nome);
		}else {
			return servicoPrestadorepository.findAll();
		}

	}
}
