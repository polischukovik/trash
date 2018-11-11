package bean;

public class LoginBean {
	
	private String name;
	private String password;

	public LoginBean(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public Object getPassword() {
		return password;
	}
}
