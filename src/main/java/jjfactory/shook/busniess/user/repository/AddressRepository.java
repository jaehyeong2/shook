package jjfactory.shook.busniess.user.repository;

import jjfactory.shook.busniess.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByUserId(Long userId);
    List<Address> findAddressesByUserId(Long userId);
}
