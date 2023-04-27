package testgenerator.bg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testgenerator.bg.entity.dto.QuestionDTO;
import testgenerator.bg.services.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/auth/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/search={search}/subject={id}")
    @CrossOrigin
    public List<QuestionDTO> getQuestions(@PathVariable String search,@PathVariable String id){

        return questionService.getQuestions(Long.parseLong(id),search);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO){
        return ResponseEntity.ok(questionService.updateQuestion(id,questionDTO));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity createClient(@RequestBody QuestionDTO questionDTO){

        return ResponseEntity.ok(questionService.createQuestion(questionDTO));
    }

    @DeleteMapping("/delete={id}")
    @CrossOrigin
    public ResponseEntity deleteClient(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

}
