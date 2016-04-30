package fr.iocean.application.media.repository;

import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.iocean.application.author.model.Author;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;
import fr.iocean.application.repository.AbstractJpaRepository;

public class MediaRepositoryImpl extends AbstractJpaRepository<Media> implements MediaRepositoryCustom {
	
	@Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }

	/**
	 * Méthode pour rechercher un média et renvoi en mode paginer
	 */
    @Override
    public PageImpl<Media> search(Pageable pageable, String title, String authorName, String type) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, title, authorName, type);
        Long count = count(title, authorName, type);
        return createSearchResult(pageable, query, count);
    }
	
    /**
     * Méthode pour compter les médias
     * @param title
     * @param authorName
     * @param type
     * @return
     */
    private Long count(String title, String authorName, String type) {
        Criteria query = getSession().createCriteria(entityClass,"media").setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, title, authorName, type);
        return (Long) query.uniqueResult();
    }

    /**
     * Méthode pour contruire une recherche
     * @param query
     * @param title
     * @param authorName
     * @param type
     */
    private void constructQuerySearch(Criteria query, String title, String authorName, String type) {

		if (!StringUtils.isEmpty(title)) {
			query.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (!StringUtils.isEmpty(authorName)) {
			//query.createAlias("author", "author") ;
			query.createCriteria("author").add(Restrictions.or(
					Restrictions.like("lastName", "%" + authorName + "%"), 
					Restrictions.like("firstName", "%" + authorName + "%")
					) 
			) ;
		}
		if (!StringUtils.isEmpty(type)) {
			Disjunction or = Restrictions.disjunction();
			for (MediaType media : MediaType.values()) {
			    if (media.name().contains(type)) {
			        or.add(Restrictions.eq("type", media));
			    }
			}
			query.add(or);
			//query.add(Restrictions.like("type", "%" + type + "%"));
		}
    }


}
