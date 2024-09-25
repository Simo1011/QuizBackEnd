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
    /**
     * Endpoint to retrieve questions for a specific subject.
     *
     * @param subjectId The unique identifier of the subject for which questions are requested.
     * @return A ResponseEntity containing a list of QuestionDTO objects.
     *         The HTTP status code will be 200 (OK) if the request is successful.
     *         The list of QuestionDTO objects represents the questions for the specified subject.
     */
    // Endpoint to get questions for a specific subject
    @GetMapping("/subjects/{subjectId}/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsForSubject(@PathVariable Long subjectId) {
        List<QuestionDTO> questions = quizService.getQuestionsForSubject(subjectId);
        return ResponseEntity.ok(questions);
    }
    /**
     * Endpoint to submit answers for a specific subject and get the result.
     *
     * @param subjectId The unique identifier of the subject for which answers are submitted.
     * @param userAnswers A list of UserAnswerDTO objects representing the user's answers.
     *                    Each UserAnswerDTO contains the questionId and the user's selected answer.
     *
     * @return A ResponseEntity containing a list of Boolean values.
     *         The HTTP status code will be 200 (OK) if the request is successful.
     *         Each Boolean value in the list represents the result of evaluating the corresponding answer.
     *         A value of true indicates that the answer is correct, while a value of false indicates that the answer is incorrect.
     */
    // Endpoint to submit answers and get the result
    @PostMapping("/subjects/{subjectId}/submit")
    public ResponseEntity<List<Boolean>> submitAnswers(@PathVariable Long subjectId, @RequestBody List<UserAnswerDTO> userAnswers) {
        List<Boolean> results = quizService.evaluateAnswers(subjectId, userAnswers);
        return ResponseEntity.ok(results);
    }
}
