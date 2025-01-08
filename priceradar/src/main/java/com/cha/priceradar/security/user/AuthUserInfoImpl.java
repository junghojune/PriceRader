package com.cha.priceradar.security.user;

import com.cha.priceradar.common.domain.UserRole;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AuthUserInfoImpl implements AuthUserInfo {
    private Long id;
    private String email;
    private String username;
    private UserRole userRole;
    private String token;
}
