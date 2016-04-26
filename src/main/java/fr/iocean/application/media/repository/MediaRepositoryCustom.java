package fr.iocean.application.media.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;

public interface MediaRepositoryCustom {

	public PageImpl<Media> search(Pageable pageable, String title, String authorName, MediaType type);

}
