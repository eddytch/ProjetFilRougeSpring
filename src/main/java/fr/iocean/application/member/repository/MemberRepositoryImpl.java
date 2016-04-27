package fr.iocean.application.member.repository;

import fr.iocean.application.member.model.Member;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

/**
 * Created by utilisateur on 27/04/2016.
 */
public class MemberRepositoryImpl extends AbstractJpaRepository<Member> implements MemberRepositoryCustom{

    @Override
    public PageImpl<Member> search(Pageable pageable, Long id, String firstName, String lastName, String email) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, id, firstName, lastName);
        Long count = count(id, firstName, lastName);
        return createSearchResult(pageable, query, count);
    }

    private Long count(Long id, String firstName, String lastName) {
        Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, id, firstName, lastName);
        return (Long) query.uniqueResult();
    }

    private void constructQuerySearch(Criteria query, Long id, String firstName, String lastName) {

        if (!StringUtils.isEmpty(id)) {
            query.add(Restrictions.like("id", "%" + id + "%"));
        }
        if (!StringUtils.isEmpty(firstName)) {
            query.add(Restrictions.like("", "%" + firstName + "%"));
        }
        if (!StringUtils.isEmpty(lastName)) {
            query.add(Restrictions.like("mediaType", "%" + lastName + "%"));
        }
    }
}
