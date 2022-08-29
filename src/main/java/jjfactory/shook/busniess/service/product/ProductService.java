package jjfactory.shook.busniess.service.product;

import jjfactory.shook.busniess.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService{
    private ProductRepository productRepository;

    public String create(){
        return "y";
    }

    public String delete(){
        return "y";
    }

    public String update(){
        return "y";
    }
}
