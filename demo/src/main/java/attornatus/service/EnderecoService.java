package attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import attornatus.exception.EntidadeEmUsoException;
import attornatus.exception.EntidadeNaoEncontradaException;
import attornatus.model.Endereco;
import attornatus.model.Pessoa;
import attornatus.model.StatusEndereco;
import attornatus.repository.EnderecoRepository;
import attornatus.repository.PessoaRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	PessoaRepository pessoaRepository;

	// findAll (método da Spring Data) - busca todos os registros
	public List<Endereco> listarEnderecos() {
		return enderecoRepository.findAll();
	}

	// findById - busca um registro pela sua chave primária
	public Endereco listarUmEnderecoId(Integer idEndereco) {
		Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
		return endereco.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de Endereço com esse Id %d", idEndereco)));
	}

	public List<Endereco> listarEnderecosDeUmaPessoa(String nome) {
		Optional<Pessoa> pessoa = pessoaRepository.findByNome(nome);
		return enderecoRepository.findByPessoa(pessoa);
	}

	public Endereco inserirEndereco(Endereco endereco, String nome) {
		Optional<Pessoa> pessoa = pessoaRepository.findByNome(nome);
		endereco.setIdEndereco(null);
		endereco.setStatus(StatusEndereco.SECUNDARIO);
		endereco.setPessoa(pessoa.get());
		;
		return enderecoRepository.save(endereco);
	}

	public Endereco atribuirEndereco(Integer idPessoa, Integer idEndereco) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		Endereco endereco = listarUmEnderecoId(idEndereco);
		if (endereco.getPessoa() != null) {
			endereco.setIdEndereco(idEndereco);
			endereco.setPessoa(pessoa.get());
			endereco.setStatus(StatusEndereco.SECUNDARIO);
		}
		return enderecoRepository.save(endereco);

	}

	public Endereco alterarStatusEndereco(Integer idEndereco, StatusEndereco status) {
		Endereco endereco = listarUmEnderecoId(idEndereco);
		if (endereco.getPessoa() != null) {
			endereco.setStatus(status);
		}
		return enderecoRepository.save(endereco);
	}

	// editar os dados de um endereco
	public Endereco editarEndereco(Endereco endereco, Integer idPessoa) {
		listarUmEnderecoId(endereco.getIdEndereco());
		Pessoa pessoa = pessoaRepository.getReferenceById(idPessoa);
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);
	}

	public void deletarEndereco(Integer idEndereco) {
		try {

			enderecoRepository.deleteById(idEndereco);

		} catch (EmptyResultDataAccessException e) {

			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro do endereço com Id %d", idEndereco));

		} catch (DataIntegrityViolationException e) {

		}
		throw new EntidadeEmUsoException(
				String.format("Endereço de Id %d não pode ser removido, pois está em uso", idEndereco));

	}
}
