package fr.iocean.application.member.repository;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.repository.AbstractJpaRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by utilisateur on 27/04/2016.
 */
public class MemberRepositoryImpl extends AbstractJpaRepository<Member> implements MemberRepositoryCustom{

    @Override
    public PageImpl<Member> search(Pageable pageable, Long id, String firstName, String lastName, String email) {
        Criteria query = createSearchCriteria(pageable);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        constructQuerySearch(query, id, firstName, lastName,email);
        Long count = count(id, firstName, lastName,email);
        return createSearchResult(pageable, query, count);
    }

    private Long count(Long id, String firstName, String lastName, String email) {
        Criteria query = getSession().createCriteria(entityClass).setProjection(Projections.countDistinct("id"));
        constructQuerySearch(query, id, firstName, lastName, email);
        return (Long) query.uniqueResult();
    }

    private void constructQuerySearch(Criteria query, Long id, String firstName, String lastName, String email) {

        if (!StringUtils.isEmpty(id)) {
            query.add(Restrictions.eq("id", id) );
        }
        if (!StringUtils.isEmpty(firstName)) {
            query.add(Restrictions.like("name", "%" + firstName + "%"));
        }
        if (!StringUtils.isEmpty(lastName)) {
            query.add(Restrictions.like("lastname", "%" + lastName + "%"));
        }
        if (!StringUtils.isEmpty(email)) {
            query.add(Restrictions.like("email", "%" + email + "%"));
        }
    }

    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}
