package guilherme.classe;

import javax.persistence.Entity;

@Entity

public class Computador extends BaseEntity {
	private String nome;
	private String numeroDeSerie;
	private String modelo;
	public Computador(String nome, String numeroDeSerie, String modelo) {
		super();
		this.nome = nome;
		this.numeroDeSerie = numeroDeSerie;
		this.modelo = modelo;
	}
	public Computador(){}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
}
