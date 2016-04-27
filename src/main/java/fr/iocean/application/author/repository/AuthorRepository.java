package fr.iocean.application.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.author.model.Author;
import org.springframework.stereotype.Repository;


public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

}
