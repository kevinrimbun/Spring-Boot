package net.javaSpring.springBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaSpring.springBoot.model.entity.DetailUser;
import net.javaSpring.springBoot.model.entity.User;

@Repository
public interface DetailUserRepository extends JpaRepository<DetailUser, Long> {
    Optional<DetailUser> findByUserId(User user);
}
