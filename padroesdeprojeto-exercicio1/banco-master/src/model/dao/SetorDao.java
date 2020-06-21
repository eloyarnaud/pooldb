package model.dao;

import java.util.List;

import model.entities.Setor;

public interface SetorDao {

	void inserir(Setor setorObj);

	void atualizar(Setor setorOb);

	void deletarPelaMatricula(String CPF);

	// SE NÃO EXISTIR, RETORNA NULL
	Setor procurarPelaMatricula(String CPF);

	List<Setor> procurarTodos();

}
