package jjfactory.shook.busniess.response;

import jjfactory.shook.busniess.domain.wish.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class WishRes {
    private LocalDateTime wishDate;
    private String productName;
    private int price;

    @Builder
    public WishRes(LocalDateTime wishDate, String productName, int price) {
        this.wishDate = wishDate;
        this.productName = productName;
        this.price = price;
    }

    public WishRes(Wish wish) {
        this.wishDate = wish.getCreateDate();
        this.productName = wish.getProduct().getName();
        this.price = wish.getProduct().getPrice();
    }
}
