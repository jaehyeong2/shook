package jjfactory.shook.busniess.service.qna;

import jjfactory.shook.busniess.domain.DeleteStatus;
import jjfactory.shook.busniess.domain.store.product.Product;
import jjfactory.shook.busniess.domain.qna.Question;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.request.qna.QuestionCreate;
import jjfactory.shook.busniess.request.qna.QuestionUpdate;
import jjfactory.shook.busniess.response.qna.MyQuestionRes;
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.PagingRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class QnaServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    QnaService qnaService;

    User wogud;
    Store store;
    Product product;
    @BeforeEach
    void setUp(){
        wogud = User.builder().name("wogud").build();
        em.persist(wogud);

        store = Store.builder().name("store").grade("5").build();
        em.persist(store);

        product = Product.builder().store(store).name("product").build();
        em.persist(product);
    }

    @Test
    @DisplayName("내 질문 조회 페이징")
    void findMyQuestions() {
        //given
        User jjj = User.builder().name("jjj").build();
        em.persist(jjj);

        for (int i = 1; i < 31; i++) {
            if(i%2 == 0){
                Question question = Question.builder().product(product).user(wogud)
                        .title("제목"+i).content("이거 할인 가능?")
                        .build();
                em.persist(question);
            }else{
                Question question = Question.builder().product(product).user(jjj)
                        .title("제목"+i).content("이거 할인 불가?")
                        .build();
                em.persist(question);
            }
        }

        //when
        PagingRes<MyQuestionRes> 검색조건x = qnaService.findMyQuestions(
                new MyPageReq(1, 10).of(), wogud,null,null,null);

        PagingRes<MyQuestionRes> 키워드검색 = qnaService.findMyQuestions(
                new MyPageReq(1, 10).of(), wogud,null,null,"불가");

        //then
        assertThat(검색조건x.getTotalCount()).isEqualTo(15);
        assertThat(검색조건x.getResultList().get(0).getTitle()).isEqualTo("제목30");

        assertThat(키워드검색.getTotalCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("질문 생성 성공")
    void createQuestion() {
        //given
        QuestionCreate dto = QuestionCreate.builder()
                .title("저기요")
                .content("이거 왤케 비쌈?")
                .productId(product.getId())
                .build();
        //when
        Long questionId = qnaService.createQuestion(dto, wogud);
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
        Question question = Question.builder().product(product).user(wogud)
                .title("하이요").content("이거 할인 가능?")
                .build();

        em.persist(question);

        //when
        qnaService.delete(question.getId(),wogud);

        //then
        assertThat(question.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

    @Test
    @DisplayName("다른 유저로 삭제 시도하면 익셉션")
    void deleteFail() {
        //given
        User wogud2 = User.builder().name("wogud2").build();
        em.persist(wogud2);

        Question question = Question.builder().product(product).user(wogud)
                .title("하이요").content("이거 할인 가능?")
                .build();

        em.persist(question);

        //expected
        assertThatThrownBy(() -> qnaService.delete(question.getId(),wogud2))
                .isInstanceOf(BusinessException.class);

    }

    @Test
    @DisplayName("질문 수정 성공")
    void update() {
        //given
        Question question = Question.builder().product(product).user(wogud)
                .title("하이요").content("이거 할인 가능?")
                .build();

        em.persist(question);

        //when
        QuestionUpdate dto = QuestionUpdate.builder().title("바뀌냐?").content("응!").build();
        qnaService.update(question.getId(),dto,wogud);

        //then
        assertThat(question.getTitle()).isEqualTo("바뀌냐?");
        assertThat(question.getContent()).isEqualTo("응!");
    }

    @Test
    @DisplayName("질문 수정 글쓴이랑 다른 유저가하면 익셉션")
    void updateFail() {
        //given
        User wogud2 = User.builder().name("wogud2").build();
        em.persist(wogud2);

        Question question = Question.builder().product(product).user(wogud)
                .title("하이요").content("이거 할인 가능?")
                .build();

        em.persist(question);
        QuestionUpdate dto = QuestionUpdate.builder().title("바뀌냐?").content("응!").build();

        //expected
        assertThatThrownBy(() -> qnaService.update(question.getId(),dto,wogud2))
                .isInstanceOf(BusinessException.class);
    }
}