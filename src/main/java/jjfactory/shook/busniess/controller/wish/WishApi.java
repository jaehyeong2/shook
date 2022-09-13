package jjfactory.shook.busniess.controller.wish;


import jjfactory.shook.busniess.response.wish.WishRes;
import jjfactory.shook.busniess.service.wish.WishService;
import jjfactory.shook.global.config.auth.PrincipalDetails;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiPagingRes;
import jjfactory.shook.global.response.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/wishes")
@RestController
public class WishApi {
    private final WishService wishService;

    @GetMapping("")
    public ApiPagingRes<WishRes> findMyWishes(@RequestParam(required = false,defaultValue = "1") int page,
                                              @RequestParam(required = false,defaultValue = "10") int size,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        return new ApiPagingRes(wishService.findMyWishes(new MyPageReq(page,size).of(),principalDetails.getUser()));
    }

    @PostMapping("")
    public ApiRes<Long> createWish(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @RequestParam Long productId){
        return new ApiRes<>(wishService.create(principalDetails.getUser(),productId));
    }

    @DeleteMapping("/{wishId}")
    public ApiRes<String> deleteWish(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @PathVariable Long wishId){
        return new ApiRes<>(wishService.delete(principalDetails.getUser(),wishId));
    }

}
