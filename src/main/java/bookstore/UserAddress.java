package bookstore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAddress {
    private String street;
    private String city;
    private String country;
    private String zipCode;
}
