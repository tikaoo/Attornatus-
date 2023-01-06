package attornatus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import attornatus.model.Endereco;
import attornatus.model.Pessoa;



public interface EnderecoRepository extends JpaRepository <Endereco, Integer>{
	
	List<Endereco> findByPessoa(Optional<Pessoa> pessoa);
	
	@Query(value = "SELECT * FROM endereco WHERE status = :status",nativeQuery = true)
	List<Endereco> findByStatus(String status);
}
