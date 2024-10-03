package GRUPO._1.FLC21444DS.Livraria.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reservas")
public class Reservas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataReserva;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDevolucaoPrevista;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDevolucaoReal;

    @OneToOne
    private Usuario usuario;
    
    @OneToMany(mappedBy = "reservas")
    private List<Livro> livro = new ArrayList();

    public Reservas() {
    	
    }
    
	public Reservas(Long id, Date dataReserva, Date dataDevolucaoPrevista, Date dataDevolucaoReal, Usuario usuario,
			List<Livro> livro) {
		super();
		this.id = id;
		this.dataReserva = dataReserva;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.dataDevolucaoReal = dataDevolucaoReal;
		this.usuario = usuario;
		this.livro = livro;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Date getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public Date getDataDevolucaoReal() {
		return dataDevolucaoReal;
	}

	public void setDataDevolucaoReal(Date dataDevolucaoReal) {
		this.dataDevolucaoReal = dataDevolucaoReal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Livro> getLivro() {
		return livro;
	}
	
	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservas other = (Reservas) obj;
		return Objects.equals(id, other.id);
	}

	
}
