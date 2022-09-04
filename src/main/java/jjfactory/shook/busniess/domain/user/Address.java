package jjfactory.shook.busniess.domain.user;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;
}
