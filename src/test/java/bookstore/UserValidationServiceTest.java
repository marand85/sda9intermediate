package bookstore;

import bookstore.users.dtos.CustomerRegistrationDTO;
import bookstore.users.entities.UserAddress;
import bookstore.users.services.UserValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class UserValidationServiceTest {

    @Test
    void shouldPassValidationWithProperUserData() {
        CustomerRegistrationDTO user = createUserWithProperData();
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(user);
        Assertions.assertTrue(errorsMap.isEmpty());
    }

    @Test
    void shouldNotPassValidationWithWrongBirthDate() {
        CustomerRegistrationDTO user = createUserWithProperData();
        user.setBirthDate("12341212");
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(user);

        Assertions.assertTrue(
                errorsMap.containsKey(UserValidationService.BIRTH_DATA_VAL_RES));
    }

    @Test
    void shouldNotPassValidationWithNullValues() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(customer);

        Assertions.assertTrue(!errorsMap.isEmpty());
    }
    @Test
    void shouldPassValidationWithWhiteSpaces() {
        CustomerRegistrationDTO customer  = createUserWithDataWithWhiteSpaces();
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(customer);

        Assertions.assertTrue(errorsMap.isEmpty());
    }
    @Test
    void shouldNotPassValidationWithBrokenPhone() {
        CustomerRegistrationDTO customer  = createUserWithDataWithWhiteSpaces();
        customer.setPhone("789456a23");
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(customer);

        Assertions.assertTrue(
                errorsMap.containsKey(UserValidationService.PHONE_VAL_RES));
    }



    private CustomerRegistrationDTO createUserWithProperData() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        customer.setFirstName("Krzysztof");
        customer.setLastName("Adfsfds");
        customer.setUserAddress(new UserAddress());
        UserAddress ua = customer.getUserAddress();
        ua.setZipCode("87-123");
        ua.setCity("łódź");
        ua.setCountry("Poland");
        ua.setStreet("Zielona");
        customer.setBirthDate("1998-10-13");
        customer.setPesel("78945612321");
        customer.setEmail("sdafadsgf@wp.pl");
        customer.setPassword("assdddsfssdfg");
        customer.setPhone("789456123");
        customer.setPreferEmails(false);
        return customer;
    }

    private CustomerRegistrationDTO createUserWithDataWithWhiteSpaces() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        customer.setFirstName(" Krzysztof ");
        customer.setLastName(" Adfsfds ");
        customer.setUserAddress(new UserAddress());
        UserAddress ua = customer.getUserAddress();
        ua.setZipCode(" 87-123");
        ua.setCity(" łódź ");
        ua.setCountry(" Poland ");
        ua.setStreet(" Zielona ");
        customer.setBirthDate(" 1998-10-13");
        customer.setPesel(" 78945612321 ");
        customer.setEmail(" sdafadsgf@wp.pl ");
        customer.setPassword(" assdddsfssdfg ");
        customer.setPhone(" 789456123 ");
        customer.setPreferEmails(false);
        return customer;
    }


}