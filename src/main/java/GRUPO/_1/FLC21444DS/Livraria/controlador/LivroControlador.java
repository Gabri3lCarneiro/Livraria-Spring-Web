package GRUPO._1.FLC21444DS.Livraria.controlador;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import GRUPO._1.FLC21444DS.Livraria.entidades.Livro;
import GRUPO._1.FLC21444DS.Livraria.repositorio.LivroRepositorio;
import GRUPO._1.FLC21444DS.Livraria.servicos.LivroServicos;


@RestController
@RequestMapping("/livro")
public class LivroControlador {

	@Autowired
	private LivroRepositorio ur;

	@Autowired
	private LivroServicos servicos;


	
	
	@GetMapping
	public ResponseEntity<List<Livro>> buscarTodos() {

		List<Livro> list = servicos.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findByid(@PathVariable Long id) {
		Livro obj = ur.getReferenceById(id);
		return ResponseEntity.ok().body(obj);
        
	}
	
	@PostMapping
	public ResponseEntity<Livro> insert(@RequestBody Livro obj) {
		obj = servicos.incerirLivro(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		servicos.removerPorId(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro obj) {
		obj = servicos.atualizarDadosLivro(obj, id);
		return ResponseEntity.ok().body(obj);
	}

}


