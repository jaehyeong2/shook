package jjfactory.shook.busniess.service.store.product;

import jjfactory.shook.busniess.domain.store.product.Product;
import jjfactory.shook.busniess.domain.store.product.ProductColor;
import jjfactory.shook.busniess.domain.store.product.ProductSize;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.repository.store.product.ProductColorRepository;
import jjfactory.shook.busniess.repository.store.product.ProductQueryRepository;
import jjfactory.shook.busniess.repository.store.product.ProductRepository;
import jjfactory.shook.busniess.repository.store.product.ProductSizeRepository;
import jjfactory.shook.busniess.repository.store.StoreRepository;
import jjfactory.shook.busniess.response.store.product.ProductDetailRes;
import jjfactory.shook.busniess.response.store.product.ProductRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService{
    private final ProductRepository productRepository;
    private final ProductColorRepository colorRepository;
    private final ProductSizeRepository sizeRepository;
    private final ProductQueryRepository productQueryRepository;
    private final StoreRepository storeRepository;

    //TODO 쿼리 3방 날아감. 쿼리 1방으로 줄이기
    @Transactional(readOnly = true)
    public ProductDetailRes findProduct(Long productId){
        Product product = getProduct(productId);
        List<String> sizes = sizeRepository.findByProductId(productId).stream()
                .map(ProductSize::getSize)
                .collect(Collectors.toList());

        List<String> colors = colorRepository.findByProductId(productId).stream()
                .map(ProductColor::getName)
                .collect(Collectors.toList());

        return new ProductDetailRes(product,colors,sizes);
    }


    @Transactional(readOnly = true)
    public PagingRes<ProductRes> findProductsByStoreId(Pageable pageable, Long storeId){
        return new PagingRes<>(productQueryRepository.findProductsByStoreId(pageable,storeId));
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(NoSuchElementException::new);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

}
