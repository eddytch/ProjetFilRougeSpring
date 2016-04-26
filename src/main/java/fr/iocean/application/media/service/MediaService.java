package fr.iocean.application.media.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;
import fr.iocean.application.media.repository.MediaRepository;
import fr.iocean.application.media.repository.MediaRepositoryCustom;

@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepository;
	
	private MediaRepositoryCustom mediaRepositoryCustom;
	
	public Media findOne(Long id) {
		return mediaRepository.findOne(id);		
	}
	
	public List<Media> findAll(int pageNumber, Long id, String title, MediaType type) {
		return new ArrayList<>();		
	}

	public MediaRepository getMediaRepository() {
		return mediaRepository;
	}

	public void setMediaRepository(MediaRepository mediaRepository) {
		this.mediaRepository = mediaRepository;
	}

	public MediaRepositoryCustom getMediaRepositoryCustom() {
		return mediaRepositoryCustom;
	}

	public void setMediaRepositoryCustom(MediaRepositoryCustom mediaRepositoryCustom) {
		this.mediaRepositoryCustom = mediaRepositoryCustom;
	}

}
