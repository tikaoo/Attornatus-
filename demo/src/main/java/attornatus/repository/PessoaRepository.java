package attornatus.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import attornatus.model.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Integer> {
	
	Optional<Pessoa> findByNome(String nome);
		
	Optional<Pessoa> deleteByNome(String nome);
	
		

}
