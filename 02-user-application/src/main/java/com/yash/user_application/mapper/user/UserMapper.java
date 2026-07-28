package com.yash.user_application.mapper.user;

import com.yash.user_application.domain.user.User;
import com.yash.user_application.dto.user.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setActive(user.isActive());

        return response;
    }

}
