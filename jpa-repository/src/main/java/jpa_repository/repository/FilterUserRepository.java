package jpa_repository.repository;

import jpa_repository.dto.PersonalInfo;
import jpa_repository.dto.UserFilter;
import jpa_repository.entity.Role;
import jpa_repository.entity.User;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
