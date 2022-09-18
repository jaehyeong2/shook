package jjfactory.shook.busniess.user.dto.res;


import jjfactory.shook.busniess.user.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddressDetailRes {
    private String alias;
    private String city;
    private String zipCode;
    private String street;
    private boolean isBaseAddr;

    public AddressDetailRes(String alias, String city, String zipCode, String street, boolean isBaseAddr) {
        this.alias = alias;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.isBaseAddr = isBaseAddr;
    }

    public AddressDetailRes(Address address) {
        this.alias = address.getAlias();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.street = address.getStreet();
        this.isBaseAddr = address.isBaseAddr();
    }
}
