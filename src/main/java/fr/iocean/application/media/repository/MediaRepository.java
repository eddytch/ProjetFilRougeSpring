package fr.iocean.application.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.iocean.application.media.model.Media;


public interface MediaRepository extends JpaRepository<Media, Long>, MediaRepositoryCustom {

	@Query("select m from Media as m join fetch m.mediaLoan as l join fetch l.leaser")
	public Media findOneFetchLoan(Long id);
	
}
