package GRUPO._1.FLC21444DS.Livraria.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GRUPO._1.FLC21444DS.Livraria.entidades.Usuario;
import GRUPO._1.FLC21444DS.Livraria.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicos {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	
	
	public List<Usuario> buscarTodos(){
		return repositorio.findAll();
	}
	
	public Usuario encontrarPoId(Long id) {
		Optional<Usuario> obj = repositorio.findById(id);
		return obj.get();
	}
	
	public void removerPorId(Long id) {
		repositorio.deleteById(id);
		
	}
	
	public Usuario atualizarDadosUsuario(Usuario usuario, Long id) {
		
		Usuario obj = repositorio.getReferenceById(id);
		atualizarDados(usuario, obj);
		return repositorio.save(obj);
	}

	private void atualizarDados(Usuario usuario, Usuario obj) {
		
		usuario.setEmail(obj.getEmail());
		usuario.setEndereco(obj.getEndereco());
		usuario.setNome(obj.getNome());
	}
	
	public Usuario incerirUsuario(Usuario obj) {
		return repositorio.save(obj);
	}
	
	
	
	
}

