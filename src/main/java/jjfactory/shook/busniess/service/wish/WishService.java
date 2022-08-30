package jjfactory.shook.busniess.service.wish;


import jjfactory.shook.busniess.repository.product.ProductRepository;
import jjfactory.shook.busniess.repository.user.UserRepository;
import jjfactory.shook.busniess.repository.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class WishService {
    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
