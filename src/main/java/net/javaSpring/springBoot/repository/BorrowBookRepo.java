package net.javaSpring.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaSpring.springBoot.model.entity.BorrowBook;

@Repository
public interface BorrowBookRepo extends JpaRepository<BorrowBook, Long> {

}
