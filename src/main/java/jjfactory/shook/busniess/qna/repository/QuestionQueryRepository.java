package jjfactory.shook.busniess.qna.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.qna.entity.QQuestion;
import jjfactory.shook.global.entity.DeleteStatus;
import jjfactory.shook.busniess.qna.dto.res.MyQuestionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static jjfactory.shook.busniess.qna.entity.QQuestion.*;


@Repository
@RequiredArgsConstructor
public class QuestionQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<MyQuestionRes> findMyQuestion(Pageable pageable,Long userId,String startDate,String endDate,String query){
        List<MyQuestionRes> result = queryFactory.select(Projections.constructor(MyQuestionRes.class, question))
                .from(question)
                .where(question.deleteStatus.eq(DeleteStatus.N),
                        question.user.id.eq(userId),
                        between(startDate,endDate),
                        contains(query))
                .offset(pageable.getOffset())
                .orderBy(question.createDate.desc())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(MyQuestionRes.class, question))
                .from(question)
                .where(question.deleteStatus.eq(DeleteStatus.N),
                        question.user.id.eq(userId),
                        between(startDate,endDate),
                        contains(query))
                .fetch().size();

        return new PageImpl<>(result,pageable,total);
    }

    private BooleanExpression between(String startDate,String endDate){
        if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
            LocalDateTime convertedStart = LocalDateTime.parse(startDate);
            LocalDateTime convertedEnd = LocalDateTime.parse(endDate);
            return question.createDate.between(convertedStart,convertedEnd);
        }
        return question.createDate.isNotNull();
    }

    private BooleanExpression contains(String query){
        if(!StringUtils.hasText(query)){
            return null;
        }
        return question.content.contains(query)
                .or(question.title.contains(query));
    }
}
