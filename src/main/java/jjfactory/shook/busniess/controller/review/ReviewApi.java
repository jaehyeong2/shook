package jjfactory.shook.busniess.controller.review;


import jjfactory.shook.busniess.request.review.ReviewCreate;
import jjfactory.shook.busniess.request.review.ReviewUpdate;
import jjfactory.shook.busniess.response.review.ReviewRes;
import jjfactory.shook.busniess.service.review.ReviewService;
import jjfactory.shook.global.config.auth.PrincipalDetails;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiPagingRes;
import jjfactory.shook.global.response.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewApi {
    private final ReviewService reviewService;

    @GetMapping("")
    public ApiPagingRes<ReviewRes> findReviews(@RequestParam Long productId,
                                               @RequestParam(required = false, defaultValue = "1") int page,
                                               @RequestParam(required = false, defaultValue = "10") int size){
        return new ApiPagingRes<>(reviewService.findReviewsByProductId(new MyPageReq(page,size).of(),productId));
    }

    @PostMapping("")
    public ApiRes<Long> create(@RequestBody ReviewCreate dto,
                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        return new ApiRes<>(reviewService.create(dto,principalDetails.getUser()));
    }

    @DeleteMapping("/{reviewId}")
    public ApiRes<String> delete(@PathVariable Long reviewId,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails){
        return new ApiRes<>(reviewService.delete(reviewId,principalDetails.getUser()));
    }

    @PutMapping("/{reviewId}")
    public ApiRes<String> update(@PathVariable Long reviewId,
                                 @RequestBody ReviewUpdate dto,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails){
        return new ApiRes<>(reviewService.update(reviewId,dto,principalDetails.getUser()));
    }
}
