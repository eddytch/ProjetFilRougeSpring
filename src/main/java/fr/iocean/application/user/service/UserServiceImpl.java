package fr.iocean.application.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.repository.UserRepository;

//CRUD Service
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	//Create
	@Override
	public User create(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	//Read
	public User findOneById(Long id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user != null) {
			return user;
		}
		throw new NotFoundException();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(Long id, User user) {
		findOneById(id);
		user.setId(id);
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User user = findOneById(id);
		userRepository.delete(user);		
	}

}
