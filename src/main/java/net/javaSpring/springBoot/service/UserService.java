package net.javaSpring.springBoot.service;

import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.model.dto.UserDto;
import net.javaSpring.springBoot.model.entity.DetailUser;

public interface UserService {
    ResponseData<Object> register(UserDto request);

    ResponseData<Object> login(UserDto request);

    // Update
    ResponseData<Object> updateUser(long id, UserDto request);
}
