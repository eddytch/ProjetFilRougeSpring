package fr.iocean.application.media.service;

import java.util.List;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;

<<<<<<< HEAD
@Service
@Getter
@Setter
public class MediaService extends AbstractService<Media> {
	
	@Autowired
	private MediaRepository mediaRepository;
	
	private MediaRepositoryCustom mediaRepositoryCustom;
	
//	public Media findOneById(Long id) {
//		return mediaRepository.findOneFetchLoan(id);		
//	}
//	
//	public List<Media> findAll(int pageNumber, Long id, String title, MediaType type) {
//		return new ArrayList<>();		
//	}

	@Override
	protected JpaRepository<Media, Long> getJpaRepository() {
		return mediaRepository;
	}
=======
public interface MediaService {
>>>>>>> cbd5ff913f80e68ecf2277ee3a60807814e531ff

	List<Media> search(Integer pageNumber , String title, String authorName, MediaType type) ;
	String size(Integer id , String title, String authorName, MediaType type) ;
}
