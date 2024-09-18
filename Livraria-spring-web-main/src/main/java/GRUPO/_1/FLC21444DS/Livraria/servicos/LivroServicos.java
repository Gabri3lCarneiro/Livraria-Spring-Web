package GRUPO._1.FLC21444DS.Livraria.servicos;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GRUPO._1.FLC21444DS.Livraria.entidades.Livro;
import GRUPO._1.FLC21444DS.Livraria.entidades.enums.LivroEstatus;
import GRUPO._1.FLC21444DS.Livraria.repositorio.LivroRepositorio;
import GRUPO._1.FLC21444DS.Livraria.servicos.execoes.ExecoesServicos;

@Service
public class LivroServicos {

	@Autowired
	private LivroRepositorio repositorio;
	
	
	public List<Livro> buscarTodos(){
		return repositorio.findAll();
	}
	
	public Livro encontrarPoId(Long id) {
		Optional<Livro> obj = repositorio.findById(id);
		return obj.get();
	}
	
	public void removerPorId(Long id) {
		repositorio.deleteById(id);
		
	}
	
	public Livro atualizarDadosLivro(Livro livro, Long id) {
		
		Livro obj = repositorio.getReferenceById(id);
		atualizarDados(livro, obj);
		return repositorio.save(obj);
	}

	private void atualizarDados(Livro livro, Livro obj) {
		
		livro.setAutor(obj.getAutor());
		livro.setDataDePubliicacao(obj.getDataDePubliicacao());
		livro.setGenero(obj.getGenero());
		livro.setNome(obj.getNome());
	}
	
	public Livro incerirLivro(Livro obj) {
		return repositorio.save(obj);
	}
	
	public void mudancaDeEstatusReserva(Set<Livro> livro) {
	   for(Livro  obj : livro) {	
		if(obj.getEstatus() == LivroEstatus.DISPONIVEL) {
			obj.setEstatus(LivroEstatus.RESERVADO);
		}
	   }
	    throw new ExecoesServicos("O livro " + ((Livro) livro).getNome() + "ja esta reservado! ");
	}
	
	public void mudancaDeEstatusDisponivel(Set<Livro> livro) {
		   for(Livro  obj : livro) {	
				if(obj.getEstatus() == LivroEstatus.RESERVADO) {
					obj.setEstatus(LivroEstatus.DISPONIVEL);
				}
			   }
			    throw new ExecoesServicos("O livro " + ((Livro) livro).getNome() + "nao esta reservado! ");
			}
	

	
}

