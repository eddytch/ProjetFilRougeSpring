package fr.iocean.application.author.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.author.model.Author;
import org.springframework.stereotype.Repository;


public interface AuthorRepositoryCustom {

	public PageImpl<Author> search(Pageable pageable, String firstName, String lastName);

}
