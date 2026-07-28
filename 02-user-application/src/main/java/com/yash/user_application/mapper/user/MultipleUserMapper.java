package com.yash.user_application.mapper.user;

import com.yash.user_application.domain.user.User;
import com.yash.user_application.dto.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultipleUserMapper {
    private final UserMapper userMapper;

    public MultipleUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserResponse> toAllUsersResponse(List<User> rawUsers) {
        List<UserResponse> users = new ArrayList<>();
        for (User user : rawUsers) {
            UserResponse responseUser = userMapper.toResponse(user);
            users.add(responseUser);
        }
        return users;
    }

}
