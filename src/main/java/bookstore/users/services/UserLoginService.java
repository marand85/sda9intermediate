package bookstore.users.services;

import bookstore.users.exceptions.PasswordDoesNotMatchException;
import bookstore.users.daos.UserDAO;
import bookstore.users.exceptions.UserNotExistsException;
import bookstore.users.dtos.CustomerLoginDTO;
import bookstore.users.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class UserLoginService {

    @Autowired
    private UserDAO userDAO;

    public void login(CustomerLoginDTO customerLoginDTO) {
        Supplier<UserNotExistsException> exceptionSupplier = () -> new UserNotExistsException("Użytkownik nie istnieje");

        User user = userDAO.getUserList()  // 1. Sprawdzenie czy user istnieje
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(customerLoginDTO.getLogin().trim()))
                .findFirst()
                .orElseThrow(exceptionSupplier);

        if (passwordIsNotCorrect(customerLoginDTO, user)) { // 2. Sprawdzenie hasła
            throw new PasswordDoesNotMatchException("Błędne hasło!");
        }
        UserContextHolder.logInUser(user);

    }

    private boolean passwordIsNotCorrect(CustomerLoginDTO customerLoginDTO, User user) {
        return !DigestUtils.sha512Hex(customerLoginDTO.getPassword().trim()).equals(user.getPasswordHash());
    }

}
