package GRUPO._1.FLC21444DS.Livraria.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import GRUPO._1.FLC21444DS.Livraria.entidades.Livro;
import GRUPO._1.FLC21444DS.Livraria.entidades.Reservas;
import GRUPO._1.FLC21444DS.Livraria.entidades.Usuario;
import GRUPO._1.FLC21444DS.Livraria.entidades.enums.LivroEstatus;
import GRUPO._1.FLC21444DS.Livraria.repositorio.LivroRepositorio;
import GRUPO._1.FLC21444DS.Livraria.repositorio.ReservasRepositorio;
import GRUPO._1.FLC21444DS.Livraria.repositorio.UsuarioRepositorio;
import GRUPO._1.FLC21444DS.Livraria.servicos.ReservasServicos;

@Configuration
@Profile("test")
public class teste implements CommandLineRunner{

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private LivroRepositorio livroRepositorio;
	
	@Autowired
	private ReservasRepositorio reservasRepositorio;
	
	@Autowired
	private ReservasServicos reservasServicos;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "123456","Gabriel" ,"gabriel@gmail" , new Date(), "endereco1");
		Usuario u2 = new Usuario(null, "654321","Lucas" ,"lucas@gmail" , new Date(), "endereco2");
		Usuario u3 = new Usuario(null, "654655","Joao" ,"joao@gmail" , new Date(), "endereco3");
		
		usuarioRepositorio.saveAll(Arrays.asList(u1,u2,u3));	
		
		Livro l1 = new Livro(null, "6565", "ABCD", "Xuxa", new Date(), "Terror", LivroEstatus.DISPONIVEL);
		Livro l2 = new Livro(null, "8888", "abapuru", "oCaraLA", new Date(), "Bibi", LivroEstatus.DISPONIVEL);	
		Livro l3 = new Livro(null, "9999", "Pumb", "NomeDoCara", new Date(), "Romance", LivroEstatus.DISPONIVEL);	
		
		livroRepositorio.saveAll(Arrays.asList(l1,l2,l3));
		
		List<Livro> list = new ArrayList<>();
		list.add(l3);
		list.add(l1);
		
		
		Reservas r1 = new Reservas(null, new Date(), new Date(), new Date(), u3, list);
		reservasServicos.incerirReservas(r1);
		
		reservasServicos.devolverLivro(r1);
	}

	
}