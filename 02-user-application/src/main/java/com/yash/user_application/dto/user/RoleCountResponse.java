package com.yash.user_application.dto.user;

import com.yash.user_application.enums.Role;

public record RoleCountResponse(Role role, Long totalUsers) {
}
