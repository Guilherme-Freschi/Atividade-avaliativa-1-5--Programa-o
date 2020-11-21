package guilherme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import guilherme.classe.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Query(value = "SELECT USER.* FROM USUARIO  AS USER " + 
			"JOIN NOTEBOOK AS NOTE ON NOTE.ID = USER.NOTEBOOK_ID " + 
			"WHERE NOTE.MODELO LIKE :modelo", nativeQuery = true)
	List<Usuario> obterTodosUsuariosPeloModeloNotebook(@Param(value = "modelo") String modelo);
	
	
	@Query(value = "SELECT USER.* FROM USUARIO  AS USER " + 
			"JOIN COMPUTADOR AS COMP ON COMP .ID = USER.COMPUTADOR_ID " + 
			"WHERE COMP.MODELO LIKE :modelo", nativeQuery = true)
	List<Usuario> obterTodosUsuariosPeloModeloComputador(@Param(value = "modelo") String modelo);
	
}
