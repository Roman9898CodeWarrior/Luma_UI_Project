package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ShippingAddressModel {

    private String firstname;
    private String lastname;
    private String streetAddress;
    private String city;
    private String stateOrProvince;
    private String postcode;
    private String country;
    private String phoneNumber;
}
