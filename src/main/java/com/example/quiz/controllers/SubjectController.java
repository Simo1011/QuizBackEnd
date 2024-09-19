package com.example.quiz.controllers;


import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.services.SubjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from localhost:3000
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);
    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{subjectId}/questions")
    public List<Question> getQuestionsBySubject(@PathVariable Long subjectId) {
        return subjectService.getQuestionsBySubject(subjectId);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @PostMapping("/{subjectId}/questions")
    public ResponseEntity<Question> addQuestionToSubject(@PathVariable Long subjectId, @RequestBody Question question) {
        logger.info("Adding question to subject with ID: {}");
        Question createdQuestion = subjectService.addQuestionToSubject(subjectId, question);
        logger.info("Created question: {}");
        return ResponseEntity.ok(createdQuestion);
    }
}