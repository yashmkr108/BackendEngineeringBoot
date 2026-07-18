package com.yash.user_application.repository;

import com.yash.user_application.domain.user.User;
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

        if (user.getId() == null) {
            user.setId(nextId);
            nextId++;
            users.add(user);

            return user;
        }

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                return user;
            }
        }

        throw new IllegalArgumentException(
                "Cannot update user with id: " + user.getId()
        );
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
