package fr.iocean.application.user.repository;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.repository.AbstractJpaRepository;
import fr.iocean.application.user.model.User;

public class UserRepositoryImpl extends AbstractJpaRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Transactional(readOnly = true)
	public Optional<User> findOneByLogin(String login) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		return Optional.of((User) criteria.uniqueResult());
	}
}
