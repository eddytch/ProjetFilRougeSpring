package fr.iocean.application.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.persistence.Identifiable;

public abstract class AbstractService<T extends Identifiable> {

	protected Class<T> entityClass;
	
	protected JpaRepository<T, Long> jpaRepository;

	protected abstract Class<T> getEntityClass();

	protected abstract JpaRepository<T, Long> getJpaRepository();

	@PostConstruct
	public void init() {
		entityClass = getEntityClass();
		jpaRepository = getJpaRepository();
	}

	// Create
	public T create(T entity) {
		return jpaRepository.save(entity);
	}

	// Read
	public T findOneById(Long id) throws NotFoundException {
		T entity = jpaRepository.findOne(id);
		if (entity != null) {
			return entity;
		}
		throw new NotFoundException();
	}

	// Read
	public List<T> findAll() {
		return jpaRepository.findAll();
	}

	// Update
	public T update(Long id, T entity) {
		findOneById(id);
		entity.setId(id);
		return jpaRepository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		T entity = findOneById(id);
		jpaRepository.delete(entity);
	}
}
