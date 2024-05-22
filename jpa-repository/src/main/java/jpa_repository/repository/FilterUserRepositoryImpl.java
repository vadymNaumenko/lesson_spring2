package jpa_repository.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jpa_repository.dto.PersonalInfo;
import jpa_repository.dto.UserFilter;
import jpa_repository.entity.Role;
import jpa_repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_COMPANY_AND_ROLE = """
           SELECT
           firstname,
           lastname,
           birth_date
           FROM users
           WHERE company_id = ?
           AND role = ?""";

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
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

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {

        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, new RowMapper<PersonalInfo>() {
            @Override
            public PersonalInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                );
            }
        },companyId,role.name());
    }
}
