package fr.iocean.application.media.service;

import java.util.List;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;

public interface MediaService {

	List<Media> search(Integer pageNumber , String title, String authorName, MediaType type) ;
	String size(Integer id , String title, String authorName, MediaType type) ;
}
