package com.yash.user_application.dto.user;

public record UserStatisticResponse(Long totalUsers,
                                    Long idSum,
                                    Long maxId,
                                    Long minId,
                                    Double averageId) {
}
