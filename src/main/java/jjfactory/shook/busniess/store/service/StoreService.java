package jjfactory.shook.busniess.store.service;

import jjfactory.shook.busniess.store.entity.Store;
import jjfactory.shook.busniess.store.repository.StoreQueryRepository;
import jjfactory.shook.busniess.store.repository.StoreRepository;
import jjfactory.shook.busniess.store.dto.res.StoreDetailRes;
import jjfactory.shook.busniess.store.dto.res.StoreRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreQueryRepository storeQueryRepository;

    @Transactional(readOnly = true)
    public PagingRes<StoreRes> findStores(Pageable pageable, String query){
        return new PagingRes<>(storeQueryRepository.findStores(pageable,query));
    }

    @Transactional(readOnly = true)
    public StoreDetailRes findStoreById(Long storeId){
        Store store = getStore(storeId);
        return new StoreDetailRes(store);
    }

    private Store getStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(NoSuchElementException::new);
    }
}
