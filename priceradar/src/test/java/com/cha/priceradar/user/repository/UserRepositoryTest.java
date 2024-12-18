package com.cha.priceradar.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.cha.priceradar.common.domain.UserRole;
import com.cha.priceradar.user.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .email("test@test.com")
                .password("password")
                .role(UserRole.ADMIN)
                .build();
        user.setCreatedBy("test");
        testEntityManager.persist(user);
    }

    @Test
    void findAllUser() {
        userRepository.findAll().forEach(System.out::println);
    }
}