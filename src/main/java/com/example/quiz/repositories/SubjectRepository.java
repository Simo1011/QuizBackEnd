package com.example.quiz.repositories;

import com.example.quiz.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SubjectRepository  extends JpaRepository<Subject,Long> {
}
