package fr.iocean.application.media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;


@Service
public interface MediaService {

	List<Media> search(Integer pageNumber , String title, String authorName, String type) ;
	String size(Integer id , String title, String authorName, String type) ;
}
