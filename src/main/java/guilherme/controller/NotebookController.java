package guilherme.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guilherme.classe.Notebook;
import guilherme.repository.NotebookRepository;


@RestController
@RequestMapping("/api/notebook")
public class NotebookController {
	@Autowired 
	private NotebookRepository notebookRepository;

	@GetMapping
    public List<Notebook> getAll() {
        return notebookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Notebook getById(@PathVariable String id) {
        Optional<Notebook> entidadeOptional = notebookRepository.findById(id);
        if(!entidadeOptional.isPresent()) {
        	throw new RegistroNaoEncontradoException();
        }
        return entidadeOptional.get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
    	notebookRepository.deleteById(id);
    }
    
    @PutMapping("/{id}")
	public void put(@PathVariable String id, @RequestBody Notebook NotebookUpdate) {
		Optional<Notebook> NotebookBuscado = notebookRepository.findById(id);
    	if(!NotebookBuscado.isPresent()) {
    		throw new RegistroNaoEncontradoException();
    	}
    	notebookRepository.save(NotebookUpdate);
	}
    
    @PostMapping
    public Notebook post(@RequestBody Notebook Notebook) {
    	if(notebookRepository.findById(Notebook.getId()).isPresent()) {
    		throw new RegistroDuplicadoException();
    	}
        return notebookRepository.save(Notebook);
    }
}
