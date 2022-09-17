package jjfactory.shook.busniess.store.api;

import io.swagger.annotations.ApiOperation;
import jjfactory.shook.busniess.store.dto.res.StoreRes;
import jjfactory.shook.busniess.product.dto.res.ProductDetailRes;
import jjfactory.shook.busniess.product.dto.res.ProductRes;
import jjfactory.shook.busniess.product.service.ProductService;
import jjfactory.shook.busniess.store.service.StoreService;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiPagingRes;
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
    @GetMapping("")
    public ApiPagingRes<StoreRes> findStores(@RequestParam(required = false,defaultValue = "1") int page,
                                             @RequestParam(required = false,defaultValue = "10") int size,
                                             @RequestParam(required = false) String query){
        return new ApiPagingRes<>(storeService.findStores(new MyPageReq(page,size).of(),query));
    }

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
