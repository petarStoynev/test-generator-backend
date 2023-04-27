package testgenerator.bg.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="question")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String a;

    private String b;

    private String c;

    private String correctAnswer;

    @JsonBackReference
    @ManyToOne
    private Subject subject;

}
