package guilherme.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import guilherme.classe.Notebook;
import guilherme.classe.Usuario;
import guilherme.repository.ComputadorRepository;
import guilherme.repository.NotebookRepository;
import guilherme.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private NotebookRepository noteBookRepository;
	
	@Autowired 
	private ComputadorRepository computadorRepository;

	@GetMapping("/busca-por-modelo-notebook/{modelo}")
	public List<Usuario> obterUsuarioPeloModeloNotebook(@PathVariable String modelo) {
		List<Usuario> usuarios = usuarioRepository.obterTodosUsuariosPeloModeloNotebook(modelo);
		return usuarios;
	}
	
	
	@GetMapping("/busca-por-modelo-computador/{modelo}")
	public List<Usuario> obterUsuarioPeloModeloComputador(@PathVariable String modelo) {
		List<Usuario> usuarios = usuarioRepository.obterTodosUsuariosPeloModeloComputador(modelo);
		return usuarios;
	}
	
	@GetMapping("/busca-media-usuario-por-notebook/{modelo}")
	public BigDecimal obterMediaPeloModeloNotebook(@PathVariable String modelo) {
		List<Usuario> usuarios = usuarioRepository.obterTodosUsuariosPeloModeloNotebook(modelo);
		List<Notebook> todosNotebooks = noteBookRepository.findAll();
		
		BigDecimal totalDeNoteBooks = new BigDecimal(todosNotebooks.size());
		BigDecimal totalDeUsuariosComMesmoNotebook = new BigDecimal(usuarios.size());
		BigDecimal media = new BigDecimal(100.0).multiply(totalDeUsuariosComMesmoNotebook).divide(totalDeNoteBooks, 2, RoundingMode.HALF_EVEN);
		return media;
		
	}
	@GetMapping("/busca-media-usuario-por-computador/{modelo}")
	public BigDecimal obterMediaPeloModeloComputador(@PathVariable String modelo) {
		List<Usuario> usuarios = usuarioRepository.obterTodosUsuariosPeloModeloComputador(modelo);
		List<Computador> todosComputadores = computadorRepository.findAll();
		
		BigDecimal totalDeComputadores = new BigDecimal(todosComputadores.size());
		BigDecimal totalDeUsuariosComMesmoComputador = new BigDecimal(usuarios.size());
		BigDecimal media = new BigDecimal(100.0).multiply(totalDeUsuariosComMesmoComputador).divide(totalDeComputadores, 2, RoundingMode.HALF_EVEN);
		return media;
	}
	
	@GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable String id) {
        Optional<Usuario> entidadeOptional = usuarioRepository.findById(id);
        if(!entidadeOptional.isPresent()) {
        	throw new RegistroNaoEncontradoException();
        }
        return entidadeOptional.get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
    	usuarioRepository.deleteById(id);
    }
    
    @PutMapping("/{id}")
	public void put(@PathVariable String id, @RequestBody Usuario UsuarioUpdate) {
		Optional<Usuario> UsuarioBuscado = usuarioRepository.findById(id);
    	if(!UsuarioBuscado.isPresent()) {
    		throw new RegistroNaoEncontradoException();
    	}
    	usuarioRepository.save(UsuarioUpdate);
	}
    
    @PostMapping
    public Usuario post(@RequestBody Usuario Usuario) {
    	if(usuarioRepository.findById(Usuario.getId()).isPresent()) {
    		throw new RegistroDuplicadoException();
    	}
        return usuarioRepository.save(Usuario);
    }
}
