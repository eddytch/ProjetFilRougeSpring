package fr.iocean.application.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.iocean.application.author.model.Author;
import fr.iocean.application.author.repository.AuthorRepository;
import fr.iocean.application.service.AbstractService;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class AuthorService extends AbstractService<Author> {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	protected JpaRepository<Author, Long> getJpaRepository() {
		return authorRepository;
	}
	
	public Author findByFirstNameAndLastName (String firstName, String lastName) {
		return authorRepository.findByFirstNameAndLastName(firstName, lastName);		
	}

	
}
