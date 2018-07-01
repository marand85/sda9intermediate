package bookstore;

public class UserRegistrationService {
    private UserValidationService userValidationService = new UserValidationService();
    private UserDAO userDAO = new UserDAO();


    private void registerUser(CustomerRegistrationDTO customer) {

        if (userExistsVer1(customer)) {
            throw new UserExistsException("User " + customer.getEmail() + " exists");
        }
        UserRegistrationDtoToUserBuilder


//        if (userExistsVer2(customer)) {
//        }
//
//        if (userExistsVer3(customer)) {
//        }
    }

    private boolean userExistsVer1(CustomerRegistrationDTO customer) {
        for (User user : userDAO.getUserList()) {
            if (user.getEmail().equals(customer.getEmail())) {
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
