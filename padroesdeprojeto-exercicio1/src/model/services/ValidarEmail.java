package model.services;

public class ValidarEmail {

	private String email;

	public ValidarEmail() {

	}

	public ValidarEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// public static void validarEmail(){
	public boolean validarEmail() {
		return (email.indexOf('@') > 0);
	}

}
