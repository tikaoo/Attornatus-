package attornatus.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import attornatus.exception.EntidadeEmUsoException;
import attornatus.exception.EntidadeNaoEncontradaException;
import attornatus.model.Endereco;
import attornatus.service.EnderecoService;

@CrossOrigin
@RestController
@RequestMapping("enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public List<Endereco> listarEnderecos() {
		List<Endereco> enderecos = enderecoService.listarEnderecos();
		return enderecos;
	}

	@GetMapping("/endereco/{idEndereco}")
	public ResponseEntity<Endereco> listarUmEnderecoId(@PathVariable Integer idEndereco) {
		Endereco endereco = enderecoService.listarUmEnderecoId(idEndereco);
		return ResponseEntity.ok().body(endereco);

	}

	@GetMapping("/enderecoPessoa/{nome}")
	public List<Endereco> listarEnderecosDeUmaPessoa(@PathVariable String nome) {
		List<Endereco> enderecos = enderecoService.listarEnderecosDeUmaPessoa(nome);
		return enderecos;

	}

	@PostMapping("/endereco/{nome}")
	public ResponseEntity<String> inserirEndereco(@RequestBody Endereco endereco, @PathVariable String nome) {
		try {
			endereco = enderecoService.inserirEndereco(endereco, nome);
			URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(endereco.getIdEndereco()).toUri();
			return ResponseEntity.created(novaUri).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	@PostMapping("/atribuirEndereco/{idPessoa}/{idEndereco}")
	public ResponseEntity<Endereco> atribuirEndereco(@PathVariable Integer idPessoa, @PathVariable Integer idEndereco) {
		enderecoService.atribuirEndereco(idPessoa, idEndereco);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/endereco/{idEndereco}/{idPessoa}")
	public ResponseEntity<String> editarFuncionario(@PathVariable Integer idEndereco, @PathVariable Integer idPessoa,
			@RequestBody Endereco endereco) {
		try {
			endereco.setIdEndereco(idEndereco);
			endereco = enderecoService.editarEndereco(endereco, idPessoa);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/endereco/{idEndereco}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Integer idEndereco) {
		try {
			enderecoService.deletarEndereco(idEndereco);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.noContent().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
