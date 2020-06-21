package banco_de_dados;

public class BdExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BdExcecao(String msg) {
		super(msg);
	}

}