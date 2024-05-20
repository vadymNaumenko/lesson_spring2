package jpa_repository.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jpa_repository.dto.UserFilter;
import jpa_repository.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;
    @Override
    public List<User> findAllByFilter(UserFilter filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteria.from(User.class);

        criteria.select(userRoot);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.firstname() != null){
            predicates.add(criteriaBuilder.like(userRoot.get("firstname"), filter.firstname()));
        }
        if (filter.lastname() != null){
            predicates.add(criteriaBuilder.like(userRoot.get("lastname"), filter.lastname()));
        }
        if (filter.birthDate() != null){
            predicates.add(criteriaBuilder.lessThan(userRoot.get("birthDate"), filter.birthDate()));
        }
        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
