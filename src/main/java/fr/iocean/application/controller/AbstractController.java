package fr.iocean.application.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.persistence.Identifiable;
import fr.iocean.application.service.AbstractService;

public abstract class AbstractController<T extends Identifiable> {

	protected AbstractService<T> service;

	protected abstract AbstractService<T> getService();

	@PostConstruct
	public void init() {
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

	public Page<T> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	
	public void update(Long id, T resource) {
		service.update(id, resource);
	}

	public void delete(Long id) {
		service.delete(id);
	}
}
