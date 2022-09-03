package jjfactory.shook.busniess.response.store;

import jjfactory.shook.busniess.domain.store.Store;
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
