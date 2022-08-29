package jjfactory.shook.busniess.service.store;

import jjfactory.shook.busniess.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public String create(){
        return "y";
    }

    public String delete(){
        return "y";
    }

    public String update(){
        return "y";
    }
}
