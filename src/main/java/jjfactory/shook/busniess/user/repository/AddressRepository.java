package jjfactory.shook.busniess.user.repository;

import jjfactory.shook.busniess.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
