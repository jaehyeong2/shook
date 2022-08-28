package jjfactory.shook.busniess.service;

import jjfactory.shook.busniess.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
}
