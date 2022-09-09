package jjfactory.shook.busniess.response.product;

import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.product.ProductColor;
import jjfactory.shook.busniess.domain.product.ProductImage;
import jjfactory.shook.busniess.domain.product.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
