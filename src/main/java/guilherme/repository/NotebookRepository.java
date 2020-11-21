package guilherme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guilherme.classe.Notebook;

public interface NotebookRepository extends JpaRepository<Notebook, String> {
	
}
