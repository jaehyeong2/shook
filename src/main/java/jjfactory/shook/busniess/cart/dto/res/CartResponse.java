package jjfactory.shook.busniess.cart.dto.res;


import jjfactory.shook.busniess.cart.entity.Cart;
import jjfactory.shook.busniess.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartResponse {
    private int price;
    private String storeName;
    private String productName;

    public CartResponse(Cart cart, Store store) {
        this.price = cart.getProduct().getPrice();
        this.storeName = store.getName();
        this.productName = cart.getProduct().getName();
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "price=" + price +
                ", storeName='" + storeName + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
