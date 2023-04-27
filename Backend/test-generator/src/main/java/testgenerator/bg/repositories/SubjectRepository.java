package testgenerator.bg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import testgenerator.bg.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Subject findByName(String name);

}
