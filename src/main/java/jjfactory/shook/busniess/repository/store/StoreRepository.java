package jjfactory.shook.busniess.repository.store;

import jjfactory.shook.busniess.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}