package com.yash.user_application.repository;

import com.yash.user_application.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BackupUserRepository implements UserRepository {

    @Override
    public User save(User user) {
        System.out.println("Saving using BACKUP repository");
        return user;
    }

    @Override
    public List<User> findAll() {
        System.out.println("Finding using BACKUP repository");
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        System.out.println("Finding by id");
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Deleting User");
    }
}
