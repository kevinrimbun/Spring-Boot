package net.javaSpring.springBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaSpring.springBoot.model.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsDeleted(boolean deleted);

    Category findByName(String name);
    
}
