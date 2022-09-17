package jjfactory.shook.busniess.store.dto.res;

import jjfactory.shook.busniess.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreRes {
    private String name;

    public StoreRes(Store store) {
        this.name = store.getName();
    }
}
