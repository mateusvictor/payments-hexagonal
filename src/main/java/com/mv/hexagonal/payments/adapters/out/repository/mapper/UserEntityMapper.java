package com.mv.hexagonal.payments.adapters.out.repository.mapper;

import com.mv.hexagonal.payments.adapters.out.repository.entity.UserEntity;
import com.mv.hexagonal.payments.application.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
  UserEntity toUserEntity(User user);

  User toUser(UserEntity userEntity);
}
