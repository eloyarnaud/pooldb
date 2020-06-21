package model.entities;

public class Setor {

	private String nomeSetor;
	private Integer id;

	public Setor() {
	}

	public Setor(String nomeSetor) {
		super();
		this.nomeSetor = nomeSetor;
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
