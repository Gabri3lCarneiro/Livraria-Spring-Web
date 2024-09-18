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

import GRUPO._1.FLC21444DS.Livraria.entidades.Usuario;
import GRUPO._1.FLC21444DS.Livraria.servicos.UsuarioServicos;


@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {


	@Autowired
	private UsuarioServicos servicos;


	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos() {

		List<Usuario> list = servicos.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findByid(@PathVariable Long id){ 
		Usuario obj = servicos.encontrarPoId(id);
		return ResponseEntity.ok().body(obj);
        
	}
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj) {
		obj = servicos.incerirUsuario(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		servicos.removerPorId(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj) {
		obj = servicos.atualizarDadosUsuario(obj, id);
		return ResponseEntity.ok().body(obj);
	}

}


