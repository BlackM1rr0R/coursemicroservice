package org.example.levelservice.service;

import org.example.levelservice.dao.AnswerDTO;
import org.example.levelservice.dao.QuestionDTO;
import org.example.levelservice.model.Question;
import org.example.levelservice.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> getRandomQuestions(int count) {
        List<Question> allQuestions = questionRepository.findAll();
        if (allQuestions.size() < count) count = allQuestions.size();
        Collections.shuffle(allQuestions, new Random());
        return allQuestions.stream().limit(count).map(this::toDTO).collect(Collectors.toList());
    }

    public Map<String, Object> checkAnswers(List<AnswerDTO> answers, String email) {
        int correct = 0;
        int wrong = 0;

        for (AnswerDTO answerDTO : answers) {
            Optional<Question> questionOpt = questionRepository.findById(answerDTO.getQuestionId());
            if (questionOpt.isPresent()) {
                Question question = questionOpt.get();
                if (question.getCorrectAnswer().equalsIgnoreCase(answerDTO.getAnswer())) {
                    correct++;
                } else {
                    wrong++;
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userEmail", email);
        result.put("correct", correct);
        result.put("wrong", wrong);
        result.put("total", answers.size());
        return result;
    }

    private QuestionDTO toDTO(Question q) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setQuestionText(q.getQuestionText());
        dto.setOptions(q.getOptions());
        return dto;
    }

    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setOptions(questionDTO.getOptions());
        question.setLevel(questionDTO.getLevel());
        question.setCorrectAnswer(questionDTO.getCorrectAnswer() != null ? questionDTO.getCorrectAnswer() : "");

        return toDTO(questionRepository.save(question));
    }
}
