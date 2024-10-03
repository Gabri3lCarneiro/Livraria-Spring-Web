package GRUPO._1.FLC21444DS.Livraria.controlador;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import GRUPO._1.FLC21444DS.Livraria.entidades.Reservas;
import GRUPO._1.FLC21444DS.Livraria.repositorio.ReservasRepositorio;
import GRUPO._1.FLC21444DS.Livraria.servicos.ReservasServicos;


@RestController
@RequestMapping("/reserva")
public class ReservasControlador {

	@Autowired
	private ReservasServicos servicos;


	
	
	@GetMapping
	public ResponseEntity<List<Reservas>> buscarTodos() {

		List<Reservas> list = servicos.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Reservas> findByid(@PathVariable Long id) {
		Reservas obj = servicos.encontrarPoId(id);
		return ResponseEntity.ok().body(obj);
        
	}
	
	@PostMapping
	public ResponseEntity<Reservas> insert(@RequestBody Reservas obj) {
		obj = servicos.incerirReservas(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	

}


