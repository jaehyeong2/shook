package jjfactory.shook.busniess.service.cart;


import jjfactory.shook.busniess.repository.cart.CartRepository;
import jjfactory.shook.busniess.response.cart.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CartService {
    private final CartRepository cartRepository;
//    private final CartQueryRepository cartQueryRepository;

//    public Page<CartResponse> findMyCarts(Long userId){
//
//    }
}
