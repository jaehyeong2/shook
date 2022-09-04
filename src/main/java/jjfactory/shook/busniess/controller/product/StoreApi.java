package jjfactory.shook.busniess.controller.product;

import jjfactory.shook.busniess.request.product.ProductCreate;
import jjfactory.shook.busniess.request.product.ProductUpdate;
import jjfactory.shook.busniess.response.product.ProductDetailRes;
import jjfactory.shook.busniess.response.product.ProductRes;
import jjfactory.shook.busniess.service.product.ProductService;
import jjfactory.shook.global.request.MyPageReq;
import jjfactory.shook.global.response.ApiRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/store")
@RequiredArgsConstructor
@RestController
public class StoreApi {
    private final ProductService productService;

    @GetMapping("/{storeId}/products/{productId}")
    public ApiRes<ProductDetailRes> findProduct(@PathVariable Long storeId,
                                                @PathVariable Long productId){
        return new ApiRes<>(productService.findProduct(productId));
    }

    @GetMapping("/{storeId}/products")
    public PagingRes<ProductRes> findProducts(@PathVariable Long storeId,
                                              @RequestParam(required = false,defaultValue = "10") int size,
                                              @RequestParam(required = false,defaultValue = "1") int page){
        return productService.findProductsByStoreId(new MyPageReq(page,size).of(),storeId);
    }

    @PostMapping("")
    public ApiRes<Long> addProduct(@PathVariable Long storeId,
                                   @RequestBody ProductCreate dto){
        return new ApiRes<>(productService.create(dto,storeId));
    }

    @DeleteMapping("/{storeId}/products/{productId}")
    public ApiRes<String> deleteProduct(@PathVariable Long productId){
        return new ApiRes<>(productService.delete(productId));
    }

    @PatchMapping("/{storeId}/products/{productId}")
    public ApiRes<String> modifyProduct(@PathVariable Long productId,
                                        @RequestBody ProductUpdate dto){
        return new ApiRes<>(productService.update(productId,dto));
    }
}
