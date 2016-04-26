package fr.iocean.application.media.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.MediaType;
import fr.iocean.application.repository.AbstractJpaRepository;

public class MediaRepositoryImpl extends AbstractJpaRepository<Media> implements MediaRepositoryCustom {
	
	@Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }

    @Override
    public PageImpl<Media> search(Pageable pageable, String title, String authorName, MediaType type) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, title, authorName, type);
        Long count = count(title, authorName, type);
        return createSearchResult(pageable, query, count);
    }
	
    private Long count(String title, String authorName, MediaType type) {
        Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, title, authorName, type);
        return (Long) query.uniqueResult();
    }

    private void constructQuerySearch(Criteria query, String title, String authorName, MediaType type) {

		if (!StringUtils.isEmpty(title)) {
			query.add(Restrictions.like("title", "%" + title + "%"));
		}
		if (!StringUtils.isEmpty(authorName)) {
			query.add(Restrictions.like("author", "%" + authorName + "%"));
		}
		if (!StringUtils.isEmpty(type)) {
			query.add(Restrictions.like("mediaType", "%" + type + "%"));
		}
    }


}
