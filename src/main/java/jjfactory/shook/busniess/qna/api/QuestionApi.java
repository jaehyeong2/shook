package jjfactory.shook.busniess.qna.api;


import jjfactory.shook.busniess.qna.dto.req.QuestionCreate;
import jjfactory.shook.busniess.qna.dto.req.QuestionUpdate;
import jjfactory.shook.busniess.qna.dto.res.MyQuestionRes;
import jjfactory.shook.busniess.qna.service.QnaService;
import jjfactory.shook.global.config.auth.PrincipalDetails;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiPagingRes;
import jjfactory.shook.global.response.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/questions")
@RequiredArgsConstructor
@RestController
public class QuestionApi {

    private final QnaService qnaService;


    @GetMapping("")
    public ApiPagingRes<MyQuestionRes> findMyQuestions(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                       @RequestParam(required = false,defaultValue = "1")int page,
                                                       @RequestParam(required = false,defaultValue = "10") int size,
                                                       @RequestParam(required = false)String startDate,
                                                       @RequestParam(required = false)String endDate,
                                                       @RequestParam(required = false) String query){
        return new ApiPagingRes<>(qnaService.findMyQuestions(
                new MyPageReq(page,size).of(),principalDetails.getUser(),startDate,endDate,query));
    }

    @PostMapping("")
    public ApiRes<Long> createQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @RequestBody QuestionCreate dto){
        return new ApiRes<>(qnaService.createQuestion(dto,principalDetails.getUser()));
    }

    @DeleteMapping("/{questionId}")
    public ApiRes<String> deleteQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable Long questionId){
        return new ApiRes<>(qnaService.delete(questionId,principalDetails.getUser()));
    }

    @PatchMapping("/{questionId}")
    public ApiRes<String> modifyQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @RequestBody QuestionUpdate dto,
                                       @PathVariable Long questionId){
        return new ApiRes<>(qnaService.update(questionId,dto,principalDetails.getUser()));
    }
}
