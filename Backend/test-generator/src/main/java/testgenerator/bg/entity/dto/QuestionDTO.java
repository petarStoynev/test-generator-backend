package testgenerator.bg.entity.dto;

import lombok.Getter;
import lombok.Setter;
import testgenerator.bg.entity.Subject;

@Getter
@Setter
public class QuestionDTO {

    private Long id;

    private String description;

    private String a;

    private String b;

    private String c;

    private String correctAnswer;

    private String subject;

}
