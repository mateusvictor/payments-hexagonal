package com.mv.hexagonal.payments.adapters.out.repository;

import com.mv.hexagonal.payments.adapters.out.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
