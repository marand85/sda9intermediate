package bookstore.users.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserAddress implements Serializable {
    private String street;
    private String city;
    private String country;
    private String zipCode;
}
