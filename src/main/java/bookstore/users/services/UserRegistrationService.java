package bookstore.users.services;

import bookstore.users.daos.UserDAO;
import bookstore.users.exceptions.UserExistsException;
import bookstore.users.dtos.CustomerRegistrationDTO;
import bookstore.users.entities.User;

public class UserRegistrationService {
    private UserValidationService userValidationService = new UserValidationService();
    private UserDAO userDAO = new UserDAO();


    public void registerUser(CustomerRegistrationDTO registrationDTO) {
        if (userExistsVer1(registrationDTO)) {
            throw new UserExistsException("User " + registrationDTO.getEmail() + " exists");
        }
        User user = CustomerRegistrationDtoToUserBuilder.rewriteToUser(registrationDTO);
        userDAO.addUser(user);
        //todo nalezy przepisac dane z  CustomerRegistrationDTO na
        // User -> zapisujac hash hasla i potem dodac uzytkownika do listy userow w userdao


//        if (userExistsVer2(customer)) {
//        }
//
//        if (userExistsVer3(customer)) {
//        }
    }

    private boolean userExistsVer1(CustomerRegistrationDTO customer) {
        for (User user : userDAO.getUserList()) {
            if (user.getEmail().trim().equalsIgnoreCase(customer.getEmail().trim())) {
                return true;
            }
        }
        return false;
    }

    private boolean userExistsVer2(CustomerRegistrationDTO customer) {
        return userDAO.getUserList().stream()
                .map(e -> e.getEmail())
                .filter(email -> email.equals(customer.getEmail()))
                .findAny().isPresent();
    }

    private boolean userExistsVer3(CustomerRegistrationDTO customer) {
        return userDAO.getUserList().stream()
                .map(user -> user.getEmail())
                .anyMatch(e -> e.equals(customer.getEmail()));
    }
}
