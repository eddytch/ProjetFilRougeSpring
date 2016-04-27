package fr.iocean.application.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import fr.iocean.application.persistence.Identifiable;
import fr.iocean.application.service.AbstractService;

public abstract class AbstractController<T extends Identifiable> {

	protected Class<T> entityClass;

	protected AbstractService<T> service;

	protected abstract Class<T> getEntityClass();

	protected abstract AbstractService<T> getService();

	@PostConstruct
	public void init() {
		entityClass = getEntityClass();
		service = getService();
	}

	public void create(T resource) {
		service.create(resource);
	}

	public T findOneById(Long id) {
		return service.findOneById(id);
	}

	public List<T> findAll() {
		return service.findAll();
	}

	public void update(Long id, T resource) {
		service.update(id, resource);
	}

	public void delete(Long id) {
		service.delete(id);
	}
}
