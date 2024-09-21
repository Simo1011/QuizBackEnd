package com.example.quiz.services;


import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Question> getQuestionsBySubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        return subject.getQuestions();
    }
    // Create a new subject
    public Subject createSubject(Subject subject) {
        // Check if subject with the same name exists
        Optional<Subject> existingSubject = subjectRepository.findByName(subject.getName());
        if (existingSubject.isPresent()) {
            throw new RuntimeException("Subject with name '" + subject.getName() + "' already exists.");
        }

        // Save the new subject
        return subjectRepository.save(subject);
    }


    // Add a question to a specific subject
    public Question addQuestionToSubject(Long subjectId, Question question) {
        // Find the subject
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        // Set the subject for the question
        question.setSubject(subject);

        // Save the question
        return questionRepository.save(question);
    }
}