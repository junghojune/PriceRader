package com.cha.priceradar.user.request;

import com.cha.priceradar.user.dto.UserDto;

public record LoginRequest(
        String email,
        String password
) {
    public UserDto toDto() {
        return UserDto.loginOf(email, password);
    }
}
