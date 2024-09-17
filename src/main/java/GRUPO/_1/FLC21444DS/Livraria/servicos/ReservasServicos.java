package GRUPO._1.FLC21444DS.Livraria.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GRUPO._1.FLC21444DS.Livraria.entidades.Reservas;
import GRUPO._1.FLC21444DS.Livraria.repositorio.ReservasRepositorio;

@Service
public class ReservasServicos {

	@Autowired
	private ReservasRepositorio repositorio;
	
	
	public List<Reservas> buscarTodos(){
		return repositorio.findAll();
	}
	
	public Reservas encontrarPoId(Long id) {
		Optional<Reservas> obj = repositorio.findById(id);
		return obj.get();
	}
	
	public void removerPorId(Long id) {
		repositorio.deleteById(id);
		
	}
	
	public Reservas atualizarDadosReservas(Reservas reserva, Long id) {
		
		Reservas obj = repositorio.getReferenceById(id);
		atualizarDados(reserva, obj);
		return repositorio.save(obj);
	}

	private void atualizarDados(Reservas reserva, Reservas obj) {
		
		reserva.setLivro(obj.getLivro());
		reserva.setUsuario(obj.getUsuario());
		reserva.setDataDevolucaoPrevista(obj.getDataDevolucaoPrevista());
	}
	
	public Reservas incerirReservas(Reservas obj) {
		return repositorio.save(obj);
	}
	
}

