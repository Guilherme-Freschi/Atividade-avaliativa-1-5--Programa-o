package guilherme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guilherme.classe.Computador;

public interface ComputadorRepository extends JpaRepository<Computador, String> {
		
}
