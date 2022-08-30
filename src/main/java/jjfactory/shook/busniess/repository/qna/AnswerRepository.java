package jjfactory.shook.busniess.repository.qna;

import jjfactory.shook.busniess.domain.qna.Answer;
import org.eclipse.jdt.internal.compiler.parser.JavadocParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
