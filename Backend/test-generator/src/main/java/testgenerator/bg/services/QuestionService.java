package testgenerator.bg.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgenerator.bg.repositories.QuestionRepository;
import testgenerator.bg.repositories.SubjectRepository;
import testgenerator.bg.entity.Question;
import testgenerator.bg.entity.Subject;
import testgenerator.bg.entity.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, SubjectRepository subjectRepository) {
        this.questionRepository = questionRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public List<QuestionDTO> getQuestions(Long id,String search){
        List<Question> questions = questionRepository.findBySubjectIdAndDescriptionStartingWith(id,search);

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setA(question.getA());
            questionDTO.setB(question.getB());
            questionDTO.setC(question.getC());
            questionDTO.setDescription(question.getDescription());
            questionDTO.setCorrectAnswer(question.getCorrectAnswer());
            questionDTO.setSubject(question.getSubject().getName());
            questionDTOS.add(questionDTO);

        }

        return questionDTOS;
    }

    @Transactional
    public List<QuestionDTO> getQuestionBySubjectName(String name){
        List<Question> questions = questionRepository.findBySubjectName(name);

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setA(question.getA());
            questionDTO.setB(question.getB());
            questionDTO.setC(question.getC());
            questionDTO.setDescription(question.getDescription());
            questionDTO.setCorrectAnswer(question.getCorrectAnswer());
            questionDTO.setSubject(question.getSubject().getName());
            questionDTOS.add(questionDTO);

        }

        Collections.shuffle(questionDTOS);

        return questionDTOS;
    }

    @Transactional
    public Question updateQuestion(Long id, QuestionDTO questionDTO){
        Question question = questionRepository.findById(id).orElseThrow();

        question.setA(questionDTO.getA());
        question.setB(questionDTO.getB());
        question.setC(questionDTO.getC());
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());

        return questionRepository.save(question);

    }

    public Question createQuestion(QuestionDTO questionDTO){

        Question question = new Question();
        question.setDescription(questionDTO.getDescription());
        question.setA(questionDTO.getA());
        question.setB(questionDTO.getB());
        question.setC(questionDTO.getC());
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());

        Subject subject = subjectRepository.findByName(questionDTO.getSubject());

        question.setSubject(subject);


        return questionRepository.save(question);

    }

    public void deleteQuestion(Long id){


        questionRepository.deleteById(id);


    }



}
