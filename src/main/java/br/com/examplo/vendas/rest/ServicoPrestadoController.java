package br.com.examplo.vendas.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.examplo.vendas.model.Cliente;
import br.com.examplo.vendas.model.ServicoPrestado;
import br.com.examplo.vendas.model.dto.ServicoPrestadoDTO;
import br.com.examplo.vendas.model.repository.ClienteRepository;
import br.com.examplo.vendas.model.repository.ServicoPrestadoRepository;
import lombok.RequiredArgsConstructor;
import util.BigDecimalConverter;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository servicoPrestadorepository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/aaaa"));
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
}