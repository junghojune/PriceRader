package com.cha.priceradar.security.jwt;

import com.cha.priceradar.common.domain.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class TokenableUser implements TokenableInfo {
    private Long id;
    private String email;
    private String username;
    private UserRole userRole;
}
