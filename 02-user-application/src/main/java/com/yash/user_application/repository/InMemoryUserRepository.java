package com.yash.user_application.repository;

import com.yash.user_application.domain.user.User;
import com.yash.user_application.exceptions.UserNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public User save(User user) {
        user.setId(nextId);
        users.add(user);
        nextId++;
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
