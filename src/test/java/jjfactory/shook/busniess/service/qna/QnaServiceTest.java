package jjfactory.shook.busniess.service.qna;

import jjfactory.shook.busniess.domain.DeleteStatus;
import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.qna.Question;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.request.qna.QuestionCreate;
import jjfactory.shook.busniess.request.qna.QuestionUpdate;
import jjfactory.shook.busniess.response.qna.MyQuestionRes;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.PagingRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class QnaServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    QnaService qnaService;

    @Test
    @DisplayName("내 질문 조회 페이징")
    void findMyQuestions() {
        //given
        User wogud = User.builder().name("wogud").build();
        User jjj = User.builder().name("jjj").build();
        em.persist(wogud);
        em.persist(jjj);

        Store store = Store.builder().name("store").grade("5").build();
        em.persist(store);

        Product product = Product.builder().store(store).name("product").build();
        em.persist(product);

        for (int i = 1; i < 31; i++) {
            if(i%2 == 0){
                Question question = Question.builder().product(product).user(wogud)
                        .title("제목"+i).content("이거 할인 가능?")
                        .build();
                em.persist(question);
            }else{
                Question question = Question.builder().product(product).user(jjj)
                        .title("제목"+i).content("이거 할인 가능?")
                        .build();
                em.persist(question);
            }
        }

        //when
        PagingRes<MyQuestionRes> result = qnaService.findMyQuestions(new MyPageReq(1, 10).of(), wogud);

        //then
        assertThat(result.getTotalCount()).isEqualTo(15);
        assertThat(result.getResultList().get(0).getTitle()).isEqualTo("제목30");

    }

    @Test
    @DisplayName("질문 생성")
    void createQuestion() {
        //given
        User wogud = User.builder().name("wogud").build();
        em.persist(wogud);

        Store store = Store.builder().name("store").grade("5").build();
        em.persist(store);

        Product product = Product.builder().store(store).name("product").build();
        em.persist(product);

        QuestionCreate dto = QuestionCreate.builder()
                .title("저기요")
                .content("이거 왤케 비쌈?")
                .build();
        //when
        Long questionId = qnaService.createQuestion(dto, wogud, product.getId());
        Question question = em.find(Question.class, questionId);

        //then
        assertThat(questionId).isNotNull();
        assertThat(question.getContent()).isEqualTo("이거 왤케 비쌈?");
        assertThat(question.getTitle()).isEqualTo("저기요");
    }

    @Test
    @DisplayName("삭제 성공")
    void delete() {
        //given
        User wogud = User.builder().name("wogud").build();
        em.persist(wogud);

        Store store = Store.builder().name("store").grade("5").build();
        em.persist(store);

        Product product = Product.builder().store(store).name("product").build();
        em.persist(product);

        Question question = Question.builder().product(product).user(wogud)
                .title("하이요").content("이거 할인 가능?")
                .build();

        em.persist(question);

        //when
        qnaService.delete(question.getId());

        //then
        assertThat(question.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

    @Test
    void update() {
        //given
        User wogud = User.builder().name("wogud").build();
        em.persist(wogud);

        Store store = Store.builder().name("store").grade("5").build();
        em.persist(store);

        Product product = Product.builder().store(store).name("product").build();
        em.persist(product);

        Question question = Question.builder().product(product).user(wogud)
                .title("하이요").content("이거 할인 가능?")
                .build();

        em.persist(question);

        //when
        QuestionUpdate dto = QuestionUpdate.builder().title("바뀌냐?").content("응!").build();
        qnaService.update(question.getId(),dto);

        assertThat(question.getTitle()).isEqualTo("바뀌냐?");
        assertThat(question.getContent()).isEqualTo("응!");
    }
}