package jjfactory.shook.busniess.qna.service;


import jjfactory.shook.global.entity.DeleteStatus;
import jjfactory.shook.busniess.store.entity.product.Product;
import jjfactory.shook.busniess.qna.entity.Question;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.store.repository.product.ProductRepository;
import jjfactory.shook.busniess.qna.repository.AnswerRepository;
import jjfactory.shook.busniess.qna.repository.QuestionQueryRepository;
import jjfactory.shook.busniess.qna.repository.QuestionRepository;
import jjfactory.shook.busniess.qna.dto.req.QuestionCreate;
import jjfactory.shook.busniess.qna.dto.req.QuestionUpdate;
import jjfactory.shook.busniess.qna.dto.res.MyQuestionRes;
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class QnaService {
    private final QuestionRepository questionRepository;
    private final QuestionQueryRepository questionQueryRepository;
    private final AnswerRepository answerRepository;
    private final ProductRepository productRepository;

    public void findQuestionDetail(){

    }

    public PagingRes<MyQuestionRes> findMyQuestions(Pageable pageable,User user,String startDate,String endDate,String query){
        return new PagingRes<>(questionQueryRepository.findMyQuestion(pageable,user.getId(),startDate,endDate,query));
    }

    public Long createQuestion(QuestionCreate dto, User user){
        Product product = getProduct(dto.getProductId());
        Question question = Question.create(dto, user, product);
        questionRepository.save(question);

        return question.getId();
    }

    public String delete(Long questionId,User user){
        Question question = getQuestion(questionId);
        if (!question.getUser().getId().equals(user.getId())){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
        question.delete();
        return "Y";
    }

    public String update(Long questionId, QuestionUpdate dto,User user){
        Question question = getQuestion(questionId);
        if (!question.getUser().getId().equals(user.getId()) || question.getDeleteStatus().equals(DeleteStatus.Y)){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
        question.modify(dto);
        return "Y";
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

    private Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(NoSuchElementException::new);
    }
}
