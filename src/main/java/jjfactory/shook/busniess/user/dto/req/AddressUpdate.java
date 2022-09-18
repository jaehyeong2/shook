package jjfactory.shook.busniess.user.dto.req;


import jjfactory.shook.busniess.user.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddressUpdate {
    private String alias;
    private String city;
    private String zipCode;
    private String street;
    private boolean isBaseAddr;

    @Builder
    public AddressUpdate(String alias, String city, String zipCode, String street, boolean isBaseAddr) {
        this.alias = alias;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.isBaseAddr = isBaseAddr;
    }
}
