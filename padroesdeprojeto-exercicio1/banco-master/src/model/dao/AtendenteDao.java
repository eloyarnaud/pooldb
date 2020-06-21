package model.dao;

import java.util.List;

import model.entities.Atendente;

public interface AtendenteDao {

	void inserir(Atendente atendenteObj);

	void atualizar(Atendente atendenteObj, String Matricula);

	void deletarPelaMatricula(String Matricula);

	// SE NÃO EXISTIR, RETORNA NULL
	Atendente procurarPelaMatricula(String Matricula);

	List<Atendente> procurarTodos();

}
