package jjfactory.shook.busniess.controller.store;

import io.swagger.annotations.ApiOperation;
import jjfactory.shook.busniess.response.store.product.ProductDetailRes;
import jjfactory.shook.busniess.response.store.product.ProductRes;
import jjfactory.shook.busniess.service.store.product.ProductService;
import jjfactory.shook.busniess.service.store.StoreService;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stores")
@RequiredArgsConstructor
@RestController
public class StoreApi {
    private final ProductService productService;
    private final StoreService storeService;

    @ApiOperation(value = "게시물 상세조회")
    @GetMapping("/{storeId}/products/{productId}")
    public ApiRes<ProductDetailRes> findProduct(@PathVariable Long storeId,
                                                @PathVariable Long productId){
        return new ApiRes<>(productService.findProduct(productId));
    }

    @ApiOperation(value = "상점 게시물 전체조회")
    @GetMapping("/{storeId}/products")
    public PagingRes<ProductRes> findProducts(@PathVariable Long storeId,
                                              @RequestParam(required = false,defaultValue = "10") int size,
                                              @RequestParam(required = false,defaultValue = "1") int page){
        return productService.findProductsByStoreId(new MyPageReq(page,size).of(),storeId);
    }

}
