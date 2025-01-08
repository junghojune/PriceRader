package com.cha.priceradar.security.jwt;

import com.cha.priceradar.common.domain.UserRole;

public interface TokenableInfo {
    Long getId();
    String getEmail();
    String getUsername();
    UserRole getUserRole();
}
