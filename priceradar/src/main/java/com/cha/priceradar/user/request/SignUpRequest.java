package com.cha.priceradar.user.request;

import com.cha.priceradar.user.dto.UserDto;

public record SignUpRequest(
        String email,
        String username,
        String password
) {
    public UserDto toDto() {
        return UserDto.signUpOf(email, username, password);
    }
}
