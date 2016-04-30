package fr.iocean.application.media.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;
import fr.iocean.application.media.repository.MediaRepository;
import fr.iocean.application.media.repository.MediaRepositoryCustom;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.repository.PageableImpl;
import fr.iocean.application.service.AbstractService;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class MediaServiceImpl extends AbstractService<Media> implements MediaService{
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private MediaRepositoryCustom mediaRepositoryImpl;
	
	@Autowired
	private Pageable pageableImpl ;

	@Override
	protected JpaRepository<Media, Long> getJpaRepository() {
		return mediaRepository;
	}
	
	
	public List<Media> search(Integer pageNumber , String title, String authorName, String type){
    	ArrayList<Media> listMembers = new ArrayList<>() ;
        PageImpl<Media> members = pageImpl(pageNumber, title, authorName, type) ;
        Iterator<Media> it = members.iterator(); 
        while (it.hasNext()) {
            Media next = it.next();
            listMembers.add(next);
        }
        return listMembers ;
    }
    
    public String size(Integer id , String title, String authorName, String type){
    	String jsonSize = "{ size :" ;
    	String jsonPages = ", pages : " ;
    	PageImpl<Media> medias = pageImpl(null, title, authorName, type) ;
    	jsonSize+=medias.getTotalElements() ;
    	jsonSize+=(jsonPages+medias.getTotalPages()+ " }") ;
		return jsonSize;
    }
    
    @Transactional
    private PageImpl<Media> pageImpl(Integer pageNumber, String title, String authorName, String type){
    	if(pageNumber != null)
    		((PageableImpl) pageableImpl).setCurrentPage(pageNumber);
        PageImpl<Media> medias = mediaRepositoryImpl.search(pageableImpl,title,authorName,type);
        return medias ;
    }

}
