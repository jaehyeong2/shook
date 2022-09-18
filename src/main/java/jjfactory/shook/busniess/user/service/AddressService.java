package jjfactory.shook.busniess.user.service;


import jjfactory.shook.busniess.user.dto.req.AddressUpdate;
import jjfactory.shook.busniess.user.dto.res.AddressDetailRes;
import jjfactory.shook.busniess.user.entity.Address;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.user.repository.AddressQueryRepository;
import jjfactory.shook.busniess.user.repository.AddressRepository;
import jjfactory.shook.busniess.user.repository.UserRepository;
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressQueryRepository addressQueryRepository;

    @Transactional(readOnly = true)
    public AddressDetailRes findDetailAddress(Long addressId){
        Address address = getAddress(addressId);
        return new AddressDetailRes(address);
    }

    @Transactional(readOnly = true)
    public List<AddressDetailRes> findMyAddresses(User user){
        return addressQueryRepository.findMyAddresses(user.getId())
                .stream().map(AddressDetailRes::new).collect(Collectors.toList());
    }

    public String deleteAddress(User user, Long addressId){
        Address address = getAddress(addressId);
        if(!user.getId().equals(address.getUser().getId())){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        address.delete();
        return "Y";
    }

    public String modifyAddress(User user, Long addressId, AddressUpdate dto){
        Address address = getAddress(addressId);
        if(!user.getId().equals(address.getUser().getId())){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        address.modify(dto);
        return "Y";
    }

    private Address getAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(NoSuchElementException::new);
        return address;
    }
}
