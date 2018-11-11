package repository;

public interface UserRepository {

	public String getPasswordHashForName(String name);
}
