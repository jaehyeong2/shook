package jjfactory.shook.busniess.repository.qna;

import jjfactory.shook.busniess.domain.qna.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
