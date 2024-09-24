package com.example.quiz.controllers;

import com.example.quiz.dto.QuestionDTO;
import com.example.quiz.dto.UserAnswerDTO;
import com.example.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for handling quiz-related operations.
 */
@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Endpoint to get questions for a specific subject
    @GetMapping("/subjects/{subjectId}/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsForSubject(@PathVariable Long subjectId) {
        List<QuestionDTO> questions = quizService.getQuestionsForSubject(subjectId);
        return ResponseEntity.ok(questions);
    }

    // Endpoint to submit answers and get the result
    @PostMapping("/subjects/{subjectId}/submit")
    public ResponseEntity<List<Boolean>> submitAnswers(@PathVariable Long subjectId, @RequestBody List<UserAnswerDTO> userAnswers) {
        List<Boolean> results = quizService.evaluateAnswers(subjectId, userAnswers);
        return ResponseEntity.ok(results);
    }
}
