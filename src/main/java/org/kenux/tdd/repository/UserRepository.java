package org.kenux.tdd.repository;

import org.kenux.tdd.domain.User;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
