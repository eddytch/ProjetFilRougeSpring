package fr.iocean.application.author.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import fr.iocean.application.author.model.Author;
import fr.iocean.application.repository.AbstractJpaRepository;

public class AuthorRepositoryImpl extends AbstractJpaRepository<Author> implements AuthorRepositoryCustom {

	@Override
	protected Class<Author> getEntityClass() {
		return Author.class;
	}

	/**
	 * Méthode pour rechercher un auteur et renvoi en mode paginer
	 */
	@Override
	public PageImpl<Author> search(Pageable pageable, String firstName, String lastName) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, firstName, lastName);
        Long count = count(firstName, lastName);
        return createSearchResult(pageable, query, count);
	}

	/**
	 * Méthode pour compter les auteurs
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	private Long count(String firstName, String lastName) {
        Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, firstName, lastName);
        return (Long) query.uniqueResult();
	}

	/**
	 * Méthode pour contruire une recherche
	 * @param query
	 * @param firstName
	 * @param lastName
	 */
	private void constructQuerySearch(Criteria query, String firstName, String lastName) {

		if (!StringUtils.isEmpty(firstName)) {
			query.add(Restrictions.like("firstName", "%" + firstName + "%"));
		}
		if (!StringUtils.isEmpty(lastName)) {
			query.add(Restrictions.like("lastName", "%" + lastName + "%"));
		}
	}

}
