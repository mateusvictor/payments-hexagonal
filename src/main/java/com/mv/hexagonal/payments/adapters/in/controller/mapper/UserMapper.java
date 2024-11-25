package com.mv.hexagonal.payments.adapters.in.controller.mapper;

import com.mv.hexagonal.payments.adapters.in.controller.request.UserRequest;
import com.mv.hexagonal.payments.application.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest customerRequest);
}
