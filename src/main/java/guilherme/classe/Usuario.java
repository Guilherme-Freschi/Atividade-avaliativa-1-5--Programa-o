package guilherme.classe;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Usuario extends BaseEntity {
	private String nome;
	private String cpf;
	private String rg;
	private String endereco;
	@ManyToOne
	private Computador computador;

	@ManyToOne
	private Notebook notebook;
	public Usuario(){}
	public Usuario(String nome, String cpf, String rg, String endereco, Computador computador, Notebook notebook) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.computador = computador;
		this.notebook = notebook;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	};
}
