package GRUPO._1.FLC21444DS.Livraria.servicos;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GRUPO._1.FLC21444DS.Livraria.entidades.Livro;
import GRUPO._1.FLC21444DS.Livraria.entidades.Reservas;
import GRUPO._1.FLC21444DS.Livraria.entidades.Usuario;
import GRUPO._1.FLC21444DS.Livraria.repositorio.ReservasRepositorio;
import GRUPO._1.FLC21444DS.Livraria.servicos.execoes.ExecoesServicos;

@Service
public class ReservasServicos {

	@Autowired
	private ReservasRepositorio repositorio;

	@Autowired
	private LivroServicos livroServicos;

	public List<Reservas> buscarTodos() {
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
		reservarLivro(obj.getLivro(), obj.getUsuario());
		return repositorio.save(obj);
	}

	public void reservarLivro(Set<Livro> livro, Usuario usuario) {

		livroServicos.mudancaDeEstatusReserva(livro);
		reservarLivrosAux(usuario, livro);
	}

	public void reservarLivrosAux(Usuario usuario, Set<Livro> livro) {
		if (usuario.getReservas().getLivro().size() >= 2) {
			throw new ExecoesServicos("Limite de reservas atingido!");
		}
		usuario.getReservas().getLivro().addAll(livro);
	}

	public void devolverLivro(Usuario usuario, Set<Livro> livros) {
		for (Livro livro : livros) {
			if (usuario.getReservas() != null) {
				livroServicos.mudancaDeEstatusDisponivel(livros);
				usuario.getReservas().getLivro().removeAll(livros);
			}

		}
	}

}
