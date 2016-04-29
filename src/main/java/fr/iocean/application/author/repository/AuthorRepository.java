package fr.iocean.application.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.author.model.Author;


public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {
	
	public Author findByFirstNameAndLastName(String firstName, String lastName);
	
}
