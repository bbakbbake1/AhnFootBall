package football;

public class Admin {
	private String id = "Admin";
	private String password = "1234";

	public Admin() {
	}

	public boolean adminLogin(String id, String password) {
		return this.id.equals(id) && this.password.equals(password);
	}
}
