package jjfactory.shook.busniess.service.product;

import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.repository.product.ProductQueryRepository;
import jjfactory.shook.busniess.repository.product.ProductRepository;
import jjfactory.shook.busniess.repository.store.StoreRepository;
import jjfactory.shook.busniess.request.ProductCreate;
import jjfactory.shook.busniess.request.ProductUpdate;
import jjfactory.shook.busniess.response.product.ProductDetailRes;
import jjfactory.shook.busniess.response.product.ProductRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService{
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public ProductDetailRes findProduct(Long productId){
        Product product = getProduct(productId);
        return new ProductDetailRes(product);
    }

    @Transactional(readOnly = true)
    public PagingRes<ProductRes> findProductsByStoreId(Pageable pageable, Long storeId){
        return new PagingRes<>(productQueryRepository.findProductsByStoreId(pageable,storeId));
    }

    public Long create(ProductCreate dto,Long storeId){
        Store store = getStore(storeId);
        Product product = Product.addProduct(dto, store);
        productRepository.save(product);
        store.incProductCount();
        return product.getId();
    }

    public String delete(Long productId){
        Product product = getProduct(productId);
        product.delete();

        Store store = product.getStore();
        store.decProductCount();
        return "y";
    }

    public String update(Long productId, ProductUpdate dto){
        Product product = getProduct(productId);
        product.modify(dto);
        return "y";
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(NoSuchElementException::new);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

}
