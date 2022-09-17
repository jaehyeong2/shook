package jjfactory.shook.busniess.wish.dto.res;

import jjfactory.shook.busniess.wish.entity.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class WishDetailRes {
    private LocalDateTime wishDate;
    private String storeName;
    private String productName;
    private int price;

    @Builder
    public WishDetailRes(LocalDateTime wishDate, String storeName, String productName, int price) {
        this.wishDate = wishDate;
        this.storeName = storeName;
        this.productName = productName;
        this.price = price;
    }

    public WishDetailRes(Wish wish) {
        this.wishDate = wish.getCreateDate();
        this.storeName = wish.getProduct().getStore().getName();
        this.productName = wish.getProduct().getName();
        this.price = wish.getProduct().getPrice();
    }
}
