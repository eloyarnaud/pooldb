package model.dao;

import java.util.List;

import model.entities.Gerente;

public interface GerenteDao {

	void inserir(Gerente gerenteObj);

	void atualizar(Gerente gerenteObj, String Matricula);

	void deletarPelaMatricula(String Matricula);

	// SE NÃO EXISTIR, RETORNA NULL
	Gerente procurarPelaMatricula(String Matricula);

	List<Gerente> procurarTodos();

	List<Gerente> retornarGerenteNome_Matricula();

}
