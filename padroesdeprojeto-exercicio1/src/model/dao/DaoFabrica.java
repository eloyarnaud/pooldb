package model.dao;

import model.dao.implementacao.AtendenteDaoJDBC;
import model.dao.implementacao.ClienteDaoJDBC;
import model.dao.implementacao.ContaDaoJDBC;
import model.dao.implementacao.GerenteDaoJDBC;

public class DaoFabrica {

	public static ClienteDao criarClienteDao() {
		return new ClienteDaoJDBC();
	}

	public static AtendenteDao criarAtendenteDao() {
		return new AtendenteDaoJDBC();
	}

	public static GerenteDao criarGerenteDao() {
		return new GerenteDaoJDBC();
	}

	public static ContaDao criarContaSimplesDao() {
		return new ContaDaoJDBC();
	}

}
