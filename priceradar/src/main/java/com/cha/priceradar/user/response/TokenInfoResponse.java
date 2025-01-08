package com.cha.priceradar.user.response;

import lombok.Builder;

@Builder
public record TokenInfoResponse(
        Long userId,
        String email,
        String accessToken,
        String refreshToken
) {
}