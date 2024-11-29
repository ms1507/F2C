package com.f2c.UserService.model;

import lombok.Data;

@Data
public class Address {
    String village;
    String landmark;
    String street;
    String city;
    String state;
    String country;
    int pincode;

}
