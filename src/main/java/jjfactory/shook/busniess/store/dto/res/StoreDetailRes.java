package jjfactory.shook.busniess.store.dto.res;

import jjfactory.shook.busniess.store.entity.Store;
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
