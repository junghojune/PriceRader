package com.cha.priceradar.user.service;

import com.cha.priceradar.security.config.UserInfoEncoder;
import com.cha.priceradar.security.jwt.JwtProvider;
import com.cha.priceradar.security.jwt.TokenInfo;
import com.cha.priceradar.security.jwt.TokenableUser;
import com.cha.priceradar.user.domain.User;
import com.cha.priceradar.user.dto.UserDto;
import com.cha.priceradar.user.repository.UserRepository;
import com.cha.priceradar.user.response.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoEncoder userInfoEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void singUp(UserDto dto) {
        userRepository.save(dto.toEntity(userInfoEncoder.hashed(dto.password())));
    }

    @Transactional
    public TokenInfoResponse login(UserDto dto) {
        User user = userRepository.findByEmail(dto.email()).orElseThrow();

        if(!userInfoEncoder.matches(dto.password(), user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }

        return toTokenInfoServiceResponse(
                generateTokenAndSaveRefresh(user), user
        );
    }

    private TokenInfo generateTokenAndSaveRefresh(User user) {

        return jwtProvider.generateToken(TokenableUser.builder()
                .id(user.getUserId())
                .userRole(user.getRole())
                .username(user.getUsername())
                .email(user.getEmail())
                .build());
    }

    private TokenInfoResponse toTokenInfoServiceResponse(final TokenInfo tokenInfo, User user) {
        return TokenInfoResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
    }
}
