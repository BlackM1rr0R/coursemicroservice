package org.example.levelservice.controller;

import org.example.levelservice.dao.AnswerDTO;
import org.example.levelservice.dao.QuestionDTO;
import org.example.levelservice.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("")
    public List<QuestionDTO> getRandomQuestions() {
        return questionService.getRandomQuestions(10);
    }

    @PostMapping("add-question")
    public QuestionDTO addQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.addQuestion(questionDTO);
    }

    @PostMapping("/submit")
    public Map<String, Object> submitAnswers(
            @RequestBody List<AnswerDTO> answers,
            @RequestHeader("X-User-Email") String email) {
        return questionService.checkAnswers(answers, email);
    }
}
