package fr.iocean.application.loan.repository;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import fr.iocean.application.loan.model.Loan;
import fr.iocean.application.repository.AbstractJpaRepository;

public class LoanRepositoryImpl extends AbstractJpaRepository<Loan> implements LoanRepositoryCustom {

	@Override
	protected Class<Loan> getEntityClass() {
		return Loan.class;
	}

	/**
	 * Méthode pour rechercher un loan et renvoi en mode paginer
	 */
	@Override
	public PageImpl<Loan> search(Pageable pageable, Date dateLoan) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, dateLoan);
        Long count = count(dateLoan);
        return createSearchResult(pageable, query, count);
	}

	/**
	 * Méthode pour compter les loans
	 * @param dateLoan
	 * @return
	 */
	private Long count(Date dateLoan) {
        Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, dateLoan);
        return (Long) query.uniqueResult();
	}

	/**
	 * Méthode pour contruire une recherche
	 * @param query
	 * @param dateLoan
	 */
	private void constructQuerySearch(Criteria query, Date dateLoan) {

		if (!StringUtils.isEmpty(dateLoan)) {
			query.add(Restrictions.like("dateLoan", "%" + dateLoan + "%"));
		}	
	}

}
