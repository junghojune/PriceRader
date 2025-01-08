package com.cha.priceradar.security.user;

import com.cha.priceradar.security.jwt.TokenableInfo;

public interface AuthUserInfo extends TokenableInfo {
    String getToken();
}
