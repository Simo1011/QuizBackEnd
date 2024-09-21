package com.example.quiz.services;

import com.example.quiz.dto.QuestionDTO;
import com.example.quiz.dto.UserAnswerDTO;
import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<QuestionDTO> getQuestionsForSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        return subject.getQuestions().stream()
                .map(question -> new QuestionDTO(
                        question.getId(),
                        question.getQuestionText(),
                        question.getOptions(),
                        question.getCorrectAnswer()  // Pass the correctAnswer here
                ))
                .collect(Collectors.toList());
    }

    // Method to evaluate the submitted answers and return a summary
    public List<Boolean> evaluateAnswers(Long subjectId, List<UserAnswerDTO> userAnswers) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        List<Question> questions = subject.getQuestions();

        return userAnswers.stream().map(answer -> {
            Question question = questions.stream()
                    .filter(q -> q.getId().equals(answer.getQuestionId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            return question.getCorrectAnswer().equals(answer.getSelectedAnswer());
        }).collect(Collectors.toList());
    }
}
