package jjfactory.shook.busniess.response;

import jjfactory.shook.busniess.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreDetailRes {
    private String name;
    private int productCount;
    private String grade;

    public StoreDetailRes(Store store) {
        this.name = store.getName();
        this.productCount = store.getProductCount();
        this.grade = store.getGrade();
    }
}
