package jjfactory.shook.busniess.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductUpdate {
    private int price;
    private int stockQuantity;
    private String name;
}
