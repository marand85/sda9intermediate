package bookstore.users.services;

import bookstore.users.dtos.CustomerRegistrationDTO;
import bookstore.users.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

public class CustomerRegistrationDtoToUserBuilder {

    public static User rewriteToUser(CustomerRegistrationDTO dto) {
        User newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setBirthDate(dto.getBirthDate());
        newUser.setEmail(dto.getEmail());
        newUser.setPesel(dto.getPesel());
        newUser.setPhone(dto.getPhone());
        newUser.setPasswordHash(DigestUtils.sha512Hex(dto.getPassword()));
        newUser.setUserAddress(dto.getUserAddress());
        newUser.setPreferEmails(dto.isPreferEmails());
        return newUser;

    }


}
