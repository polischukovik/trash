package repository;

import java.util.HashMap;
import java.util.Map;

public class LoginRepositoryPrimitive implements UserRepository{
Map<String, String> loginData = new HashMap<>();
	
	public LoginRepositoryPrimitive() {
		loginData.put("admin", "pass");
		loginData.put("user", "user");
	}
	
	public String getPasswordHashForName(String name) {
		return loginData.get(name);
	}

}	
