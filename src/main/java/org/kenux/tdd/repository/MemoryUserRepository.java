package org.kenux.tdd.repository;

import org.kenux.tdd.domain.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        this.users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return this.users.get(id);
    }
}
