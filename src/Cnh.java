
public class Cnh {
	private String Cpf_cnh, Register_cnh, FirstName_cnh, LastName_cnh, EmissionAt_cnh;
	
	public Cnh() {
		setCpf_cnh(""); 
		setRegister_cnh(""); 
		setFirstName_cnh(""); 
		setLastName_cnh(""); 
		setEmissionAt_cnh("");
	}

	public String getCpf_cnh() {
		return Cpf_cnh;
	}

	public void setCpf_cnh(String cpf_cnh) {
		Cpf_cnh = cpf_cnh;
	}

	public String getRegister_cnh() {
		return Register_cnh;
	}

	public void setRegister_cnh(String register_cnh) {
		this.Register_cnh = register_cnh;
	}

	public String getFirstName_cnh() {
		return FirstName_cnh;
	}

	public void setFirstName_cnh(String firstName_cnh) {
		FirstName_cnh = firstName_cnh;
	}

	public String getLastName_cnh() {
		return LastName_cnh;
	}

	public void setLastName_cnh(String lastName_cnh) {
		LastName_cnh = lastName_cnh;
	}

	public String getEmissionAt_cnh() {
		return EmissionAt_cnh;
	}

	public void setEmissionAt_cnh(String emissionAt_cnh) {
		EmissionAt_cnh = emissionAt_cnh;
	}
	
	public void conectaInterface(int id) {
		if(id == 1) {
			setCpf_cnh("123546987"); setRegister_cnh("33222545"); setFirstName_cnh("Ademar"); setLastName_cnh("Apior"); setEmissionAt_cnh("20/02/16");
		}
	}
}
