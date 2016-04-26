package fr.iocean.application.user.service;

import java.util.List;

import fr.iocean.application.user.model.User;

//CRUD
public interface UserService {
	
	// Create
	public User create(User user);

	// Read
	public User findOneById(Long id);

	public List<User> findAll();

	// Update
	public User update(Long id, User user);

	// Delete
	public void delete(Long id);

}
