package com.cha.priceradar.user.repository;

import com.cha.priceradar.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
