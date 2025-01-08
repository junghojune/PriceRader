package com.cha.priceradar.user.service;

import com.cha.priceradar.security.config.UserInfoEncoder;
import com.cha.priceradar.user.dto.UserDto;
import com.cha.priceradar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoEncoder userInfoEncoder;

    @Transactional
    public void singUp(UserDto dto) {
        userRepository.save(dto.toEntity(userInfoEncoder.hashed(dto.password())));
    }
}
