package jjfactory.shook.busniess.cart.api;


import jjfactory.shook.busniess.cart.dto.res.CartResponse;
import jjfactory.shook.busniess.cart.service.CartService;
import jjfactory.shook.global.config.auth.PrincipalDetails;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiPagingRes;
import jjfactory.shook.global.response.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/carts")
@RequiredArgsConstructor
@RestController
public class CartApi {
    private final CartService cartService;

    @GetMapping("")
    public ApiPagingRes<CartResponse> findMyCarts(@RequestParam(required = false, defaultValue = "1") int page,
                                                  @RequestParam(required = false,defaultValue = "10") int size,
                                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        return new ApiPagingRes<>(cartService.findMyCarts(new MyPageReq(page,size).of(),principalDetails.getUser()));
    }

    @PostMapping("")
    public ApiRes<Long> addProduct(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @RequestParam Long productId){
        return new ApiRes<>(cartService.addProductToCart(principalDetails.getUser(),productId));
    }

    @DeleteMapping("/{productId}")
    public ApiRes<String> deleteProduct(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @RequestParam Long productId){
        return new ApiRes<>(cartService.deleteProduct(principalDetails.getUser(),productId));
    }
}
