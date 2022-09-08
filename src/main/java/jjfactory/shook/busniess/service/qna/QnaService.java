package jjfactory.shook.busniess.service.qna;


import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.qna.Question;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.repository.product.ProductRepository;
import jjfactory.shook.busniess.repository.qna.AnswerRepository;
import jjfactory.shook.busniess.repository.qna.QuestionQueryRepository;
import jjfactory.shook.busniess.repository.qna.QuestionRepository;
import jjfactory.shook.busniess.request.qna.QuestionCreate;
import jjfactory.shook.busniess.request.qna.QuestionUpdate;
import jjfactory.shook.busniess.response.qna.MyQuestionRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    //TODO 디테일 조회도 필요. 이건 ANSWER랑 같이 조인해서

    public PagingRes<MyQuestionRes> findMyQuestions(Pageable pageable,User user){
        return new PagingRes<>(questionQueryRepository.findMyQuestion(pageable,user.getId()));
    }

    public Long createQuestion(QuestionCreate dto, User user){
        Product product = getProduct(dto.getProductId());
        Question question = Question.create(dto, user, product);
        questionRepository.save(question);

        return question.getId();
    }

    public String delete(Long questionId,User user){
        Question question = getQuestion(questionId);
        question.delete();
        return "Y";
    }

    public String update(Long questionId, QuestionUpdate dto,User user){
        Question question = getQuestion(questionId);
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
