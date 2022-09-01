package jjfactory.shook.busniess.service.product;

import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.repository.product.ProductRepository;
import jjfactory.shook.busniess.repository.store.StoreRepository;
import jjfactory.shook.busniess.request.ProductCreate;
import jjfactory.shook.busniess.request.ProductUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService{
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public String create(ProductCreate dto,Long storeId){
        Store store = getStore(storeId);
        Product product = Product.addProduct(dto, store);
        productRepository.save(product);
        return "y";
    }

    public String delete(Long productId){
        Product product = getProduct(productId);
        product.delete();
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
