package fr.iocean.application.media.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;
import fr.iocean.application.media.repository.MediaRepository;
import fr.iocean.application.media.repository.MediaRepositoryCustom;
import fr.iocean.application.service.AbstractService;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class MediaService extends AbstractService<Media> {
	
	@Autowired
	private MediaRepository mediaRepository;
	
	private MediaRepositoryCustom mediaRepositoryCustom;
	
//	public Media findOne(Long id) {
//		return mediaRepository.findOne(id);		
//	}
//	
//	public List<Media> findAll(int pageNumber, Long id, String title, MediaType type) {
//		return new ArrayList<>();		
//	}

	@Override
	protected Class<Media> getEntityClass() {
		return Media.class;
	}

	@Override
	protected JpaRepository<Media, Long> getJpaRepository() {
		return mediaRepository;
	}

}
