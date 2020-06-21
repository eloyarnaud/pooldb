package model.dao;

import java.util.List;

import model.entities.Conta;

public interface ContaDao {

	void inserir(Conta contaSimplesObj);

	void atualizar(Conta contaSimplesObj, int id);

	void deletarContaPeloId(int id);

	// SE N�O EXISTIR, RETORNA NULL
	Conta procurarContaPeloCPF(String CPF);

	List<Conta> procurarTodos();

}
