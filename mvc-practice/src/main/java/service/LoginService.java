package service;

import bean.LoginBean;
import repository.LoginRepositoryPrimitive;

public class LoginService {
	
	LoginRepositoryPrimitive loginRepository = new LoginRepositoryPrimitive();

	public boolean authorize(LoginBean loginBean) {
		String passwordHash = loginRepository.getPasswordHashForName(loginBean.getName());
		return passwordHash != null && passwordHash.equals(loginBean.getPassword());
	}

}
