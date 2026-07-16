package com.yash.user_application.service;

import com.yash.user_application.domain.User;
import com.yash.user_application.dto.user.CreateUserRequest;
import com.yash.user_application.dto.user.UserResponse;
import com.yash.user_application.enums.Role;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    public List<User> getAllUsers() {
        User user = new User(1L, "Yash Makkar", "yashmakkar777@gmail.com", "Yash", "Makkar", LocalDate.of(2004, 6, 4), Role.STUDENT);
        return List.of(user);
    }

    public UserResponse createUser(CreateUserRequest request) {

        User user = new User();

        user.setId(1L);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.STUDENT);

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;

    }
}
