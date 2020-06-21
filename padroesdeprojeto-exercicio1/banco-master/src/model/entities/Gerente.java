package model.entities;

import java.io.Serializable;
import java.util.Random;

import model.services.Matricula;

public class Gerente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCompleto;
	private String matricula;
	private String email;
	private String telefone;
	private Integer setorResponsavel;

	public Gerente() {
	}

	public Gerente(String email, String telefone, Integer setorResponsavel) {
		super();
		this.email = email;
		this.telefone = telefone;
		this.setorResponsavel = setorResponsavel;
	}

	public Gerente(String nomeCompleto, String matricula, String email, String telefone, Integer setorResponsavel) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.setorResponsavel = setorResponsavel;
	}

	Random random = new Random();

	public String getNome_completo() {
		return nomeCompleto;
	}

	public void setNome_completo(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getSetorResponsavel() {
		return setorResponsavel;
	}

	public void setSetorResponsavel(Integer setorResponsavel) {
		this.setorResponsavel = setorResponsavel;
	}

	public String gerarMatriculaGerente() {

		String inicioMatriculaGerente = "G" + Matricula.gerarParteInicialMatricula();

		if (Matricula.mesParaMatricula().length() > 1) {

			for (int i = 0; i < 4; i++) {

				Integer matricula = random.nextInt(9);
				inicioMatriculaGerente = inicioMatriculaGerente + matricula;
			}

		} else {

			for (int i = 0; i < 5; i++) {

				Integer matricula = random.nextInt(9);
				inicioMatriculaGerente = inicioMatriculaGerente + matricula;
			}
		}

		String concatenar = inicioMatriculaGerente;

		setMatricula(concatenar);
		return concatenar;

	}

}
