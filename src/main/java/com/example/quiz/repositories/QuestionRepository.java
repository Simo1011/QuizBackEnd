package com.example.quiz.repositories;

import com.example.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository  extends JpaRepository<Question,Long> {
    List<Question> findBySubjectId(Long subjectId);
}
