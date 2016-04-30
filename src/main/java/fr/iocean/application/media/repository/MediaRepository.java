package fr.iocean.application.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.media.model.Media;
import org.springframework.stereotype.Repository;


public interface MediaRepository extends JpaRepository<Media, Long>, MediaRepositoryCustom {

	
}
