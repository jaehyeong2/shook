package jjfactory.shook.busniess.response.store.product;

import jjfactory.shook.busniess.domain.store.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDetailRes {
    private String name;
    private int price;
    private String description;
    private String enrollDate;
    private List<String> sizeList;
    private List<String> colorList;

    public ProductDetailRes(Product product, List<String> colors, List<String> sizes) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.enrollDate = product.getCreateDate().format(formatter);
        this.colorList = colors;
        this.sizeList = sizes;
    }
}
