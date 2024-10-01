package GRUPO._1.FLC21444DS.Livraria.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String cpf;        
	    private String nome;                                                                                                                                                             
	    private String email; 
	    
	    @DateTimeFormat(pattern = "dd/MM/yyyy")
	    private Date dataDeNascimento;
	    private String endereco;
	    
	    
	    @OneToOne(mappedBy = "usuario")
	    private Reservas reservas;
	 
	    
		public Usuario() {
			
		}
	    
		public Usuario(Long id, String cpf, String nome, String email, Date dataDeNascimento, String endereco) {
			super();
			this.id = id;
			this.cpf = cpf;
			this.nome = nome;
			this.email = email;
			this.dataDeNascimento = dataDeNascimento;
			this.endereco = endereco;
		}

		
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getDataDeNascimento() {
			return dataDeNascimento;
		}

		public void setDataDeNascimento(Date dataDeNascimento) {
			this.dataDeNascimento = dataDeNascimento;
		}

		public String getEndereco() {
			return endereco;
		}
		
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		@JsonIgnore
		public Reservas getReservas() {
			return reservas;
		}
		@JsonIgnore
		public void setReservas(Reservas reservas) {
			this.reservas = reservas;
		}
		

		@Override
		public int hashCode() {
			return Objects.hash(cpf, id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Usuario other = (Usuario) obj;
			return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
		}
}
