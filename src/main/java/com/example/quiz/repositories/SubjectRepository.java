package com.example.quiz.repositories;

import com.example.quiz.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SubjectRepository  extends JpaRepository<Subject,Long> {
    Optional<Subject> findByName(String name); // Query method to find by name
}
