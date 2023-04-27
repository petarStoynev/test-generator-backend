package testgenerator.bg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testgenerator.bg.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findBySubjectIdAndDescriptionStartingWith(Long subjectId,String search);

    List<Question> findBySubjectName(String subjectName);



}
