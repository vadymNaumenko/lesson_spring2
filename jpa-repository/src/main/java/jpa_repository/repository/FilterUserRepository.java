package jpa_repository.repository;

import jpa_repository.dto.UserFilter;
import jpa_repository.entity.User;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
