
public class Documento {
	
	public Documento(String cpf, String register, String firstName, String lastName, String emissionAt) {
		this.cpf = cpf;
		this.register = register;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emissionAt = emissionAt;
	}

	private String cpf, register, firstName, lastName, emissionAt;


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmissionAt() {
		return emissionAt;
	}

	public void setEmissionAt(String emissionAt) {
		this.emissionAt = emissionAt;
	}
	
}
