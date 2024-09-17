package GRUPO._1.FLC21444DS.Livraria.entidades.DTO;

import java.util.Date;

import GRUPO._1.FLC21444DS.Livraria.entidades.Usuario;

public record UsuarioRespostaDTO(Long id, String cpf, String nome, String email, Date dataDeNascimento, String endereco) {

	public UsuarioRespostaDTO(Usuario usuario) {
		
		this(usuario.getId(), usuario.getCpf(), usuario.getNome(), usuario.getEmail(), usuario.getDataDeNascimento(), usuario.getEndereco());
	}
}
