package jjfactory.shook.busniess.response.cart;


import jjfactory.shook.busniess.domain.cart.Cart;
import jjfactory.shook.busniess.domain.store.Store;
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
