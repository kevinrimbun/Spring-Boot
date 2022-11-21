package net.javaSpring.springBoot.validator;

import java.util.Optional;

import net.javaSpring.springBoot.exception.custom.CustomNotFound;
import net.javaSpring.springBoot.model.entity.User;

public class UserValidator {
    public void validateUserNotFound(Optional<User> userOpt) throws Exception {
        if (userOpt.isEmpty()){
            throw new CustomNotFound("User Not Found");
        }
    }
}
