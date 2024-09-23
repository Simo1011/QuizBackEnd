package com.example.quiz.controllers;


import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.services.SubjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.server.ResponseStatusException;

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
    /**
     * Retrieves a list of questions associated with a specific subject.
     *
     * @param subjectId The unique identifier of the subject. This is obtained from the URL path variable.
     * @return A list of questions associated with the specified subject. If no questions are found, an empty list is returned.
     * @throws ResponseStatusException If the subject with the given ID does not exist.
     */

    @GetMapping("/{subjectId}/questions")
    public List<Question> getQuestionsBySubject(@PathVariable Long subjectId) {
        return subjectService.getQuestionsBySubject(subjectId);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        try {
            Subject createdSubject = subjectService.createSubject(subject);
            return ResponseEntity.ok(createdSubject);
        } catch (RuntimeException e) {
            // If the subject already exists, return a conflict status
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/{subjectId}/questions")
    public ResponseEntity<Question> addQuestionToSubject(@PathVariable Long subjectId, @RequestBody Question question) {
        logger.info("Adding question to subject with ID: {}");
        Question createdQuestion = subjectService.addQuestionToSubject(subjectId, question);
        logger.info("Created question: {}");
        return ResponseEntity.ok(createdQuestion);
    }
}