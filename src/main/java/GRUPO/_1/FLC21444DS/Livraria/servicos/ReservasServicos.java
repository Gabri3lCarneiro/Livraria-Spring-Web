package GRUPO._1.FLC21444DS.Livraria.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import GRUPO._1.FLC21444DS.Livraria.entidades.Reservas;
import GRUPO._1.FLC21444DS.Livraria.entidades.enums.LivroEstatus;
import GRUPO._1.FLC21444DS.Livraria.repositorio.LivroRepositorio;
import GRUPO._1.FLC21444DS.Livraria.repositorio.ReservasRepositorio;

@Service
public class ReservasServicos {

	@Autowired
	private ReservasRepositorio reservasRepositorio;

	@Autowired
	private LivroRepositorio livroRepositorio;

	public List<Reservas> buscarTodos() {
		return reservasRepositorio.findAll();
	}

	public Reservas encontrarPoId(Long id) {
		Optional<Reservas> obj = reservasRepositorio.findById(id);
		return obj.get();
	}

	public void removerPorId(Long id) {
		try {
			Optional<Reservas> obj = reservasRepositorio.findById(id);
			for (int i = 0; i <= obj.get().getLivro().size() - 1; i++) {
				if (obj.get().getLivro().get(i).getEstatus() == LivroEstatus.DISPONIVEL) {
					obj.get().getLivro().get(i).setEstatus(LivroEstatus.RESERVADO);
					livroRepositorio.save(obj.get().getLivro().get(i));

				} else {
					throw new ExecoesServicos("O livro: " + obj.get().getLivro() + " nao esta reservado");
				}

			}

		} catch (InvalidDataAccessApiUsageException e) {
			throw new ExecoesServicos(e.getMessage());

		}
		reservasRepositorio.deleteById(id);

	}

	public Reservas atualizarDadosReservas(Reservas reserva, Long id) {

		Reservas obj = reservasRepositorio.getReferenceById(id);
		atualizarDados(reserva, obj);
		return reservasRepositorio.save(obj);
	}

	private void atualizarDados(Reservas reserva, Reservas obj) {

		reserva.setLivro(obj.getLivro());
		reserva.setUsuario(obj.getUsuario());
		reserva.setDataDevolucaoPrevista(obj.getDataDevolucaoPrevista());
		reservasRepositorio.save(obj);
	}

	public Reservas incerirReservas(Reservas obj) {
		try {

			for (int i = 0; i <= obj.getLivro().size() - 1; i++) {
				if (obj.getLivro().get(i).getEstatus() == LivroEstatus.DISPONIVEL) {
					obj.getLivro().get(i).setEstatus(LivroEstatus.RESERVADO);
					livroRepositorio.save(obj.getLivro().get(i));

				} else {
					throw new ExecoesServicos("O livro: " + obj.getLivro() + " ja esta reservado");
				}

			}
			return reservasRepositorio.save(obj);

		} catch (InvalidDataAccessApiUsageException e) {
			throw new ExecoesServicos(e.getMessage());

		}
		
	}
	
	public Reservas devolverLivro(Reservas obj) {
		try {

			for (int i = 0; i <= obj.getLivro().size() - 1; i++) {
				if (obj.getLivro().get(i).getEstatus() == LivroEstatus.RESERVADO) {
					obj.getLivro().get(i).setEstatus(LivroEstatus.DISPONIVEL);
					livroRepositorio.save(obj.getLivro().get(i));
					

				} else {
					throw new ExecoesServicos("O livro: " + obj.getLivro() + " nao esta reservado! ");
				}

			}
			return reservasRepositorio.save(obj);

		} catch (InvalidDataAccessApiUsageException e) {
			throw new ExecoesServicos(e.getMessage());

		}
		
	}
	
	
}
