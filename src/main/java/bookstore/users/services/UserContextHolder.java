package bookstore.users.services;

import bookstore.users.dtos.UserLoggedInDto;
import bookstore.users.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserContextHolder {

    private static UserLoggedInDto userLoggedInDto;

    public static void logInUser(User user) {
        userLoggedInDto = new UserLoggedInDto(user.getEmail());
    }

    public static String getUserLoggedIn() {
        return userLoggedInDto == null ? null : userLoggedInDto.getLogin();
    }

}
