package jjfactory.shook.busniess.qna.repository;

import jjfactory.shook.busniess.qna.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
