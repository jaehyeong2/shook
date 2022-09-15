package jjfactory.shook.busniess.service.store;

import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.repository.store.StoreQueryRepository;
import jjfactory.shook.busniess.repository.store.StoreRepository;
import jjfactory.shook.busniess.request.store.StoreCreate;
import jjfactory.shook.busniess.request.store.StoreUpdate;
import jjfactory.shook.busniess.response.store.StoreDetailRes;
import jjfactory.shook.busniess.response.store.StoreRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
