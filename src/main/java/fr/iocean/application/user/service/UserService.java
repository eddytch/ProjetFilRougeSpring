package fr.iocean.application.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.service.AbstractService;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

//CRUD Service
@Service
@Transactional
@Getter
@Setter
public class UserService extends AbstractService<User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public  PasswordEncoder passwordEncoder;

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	protected JpaRepository<User, Long> getJpaRepository() {
		return userRepository;
	}


	public User create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User update(Long id, User user) {
		findOneById(id);
		user.setId(id);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
