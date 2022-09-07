package jjfactory.shook.busniess.repository.qna;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.domain.DeleteStatus;
import jjfactory.shook.busniess.domain.qna.QQuestion;
import jjfactory.shook.busniess.response.qna.MyQuestionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.domain.qna.QQuestion.*;

@Repository
@RequiredArgsConstructor
public class QuestionQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<MyQuestionRes> findMyQuestion(Pageable pageable,Long userId){
        List<MyQuestionRes> result = queryFactory.select(Projections.constructor(MyQuestionRes.class, question))
                .from(question)
                .where(question.deleteStatus.eq(DeleteStatus.N),
                        question.user.id.eq(userId))
                .offset(pageable.getOffset())
                .orderBy(question.createDate.desc())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(MyQuestionRes.class, question))
                .from(question)
                .where(question.deleteStatus.eq(DeleteStatus.N),
                        question.user.id.eq(userId))
                .fetch().size();

        return new PageImpl<>(result,pageable,total);
    }
}
