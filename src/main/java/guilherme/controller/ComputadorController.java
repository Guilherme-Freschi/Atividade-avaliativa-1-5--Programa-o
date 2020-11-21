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

import guilherme.classe.Computador;
import guilherme.repository.ComputadorRepository;


@RestController
@RequestMapping("/api/computador")
public class ComputadorController {
	@Autowired 
	private ComputadorRepository computadorRepository;

	@GetMapping
    public List<Computador> getAll() {
        return computadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Computador getById(@PathVariable String id) {
        Optional<Computador> entidadeOptional = computadorRepository.findById(id);
        if(!entidadeOptional.isPresent()) {
        	throw new RegistroNaoEncontradoException();
        }
        return entidadeOptional.get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
    	computadorRepository.deleteById(id);
    }
    
    @PutMapping("/{id}")
	public void put(@PathVariable String id, @RequestBody Computador NotebookUpdate) {
		Optional<Computador> NotebookBuscado = computadorRepository.findById(id);
    	if(!NotebookBuscado.isPresent()) {
    		throw new RegistroNaoEncontradoException();
    	}
    	computadorRepository.save(NotebookUpdate);
	}
    
    @PostMapping
    public Computador post(@RequestBody Computador Computador) {
    	if(computadorRepository.findById(Computador.getId()).isPresent()) {
    		throw new RegistroDuplicadoException();
    	}
        return computadorRepository.save(Computador);
    }
}