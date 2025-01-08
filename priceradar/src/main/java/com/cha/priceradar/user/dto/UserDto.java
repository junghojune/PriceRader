package com.cha.priceradar.user.dto;

import com.cha.priceradar.common.domain.UserRole;
import com.cha.priceradar.user.domain.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record UserDto(
        Long userId,
        String email,
        String username,
        String password,
        UserRole role,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        LocalDateTime deletedAt,
        String deletedBy,
        Boolean isDeleted
) {
    public static UserDto from(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .modifiedAt(user.getModifiedAt())
                .modifiedBy(user.getModifiedBy())
                .deletedAt(user.getDeletedAt())
                .deletedBy(user.getDeletedBy())
                .isDeleted(user.getIsDeleted())
                .build();
    }

    public static UserDto signUpOf(String email, String username, String password) {
        return UserDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

    public User toEntity(String encodePassword) {
        return User.create(email, username, encodePassword);
    }
}
