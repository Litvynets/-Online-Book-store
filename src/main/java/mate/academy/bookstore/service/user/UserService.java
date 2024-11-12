package mate.academy.bookstore.service.user;

import mate.academy.bookstore.dto.user.UserRegistrationDto;
import mate.academy.bookstore.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationDto registrationDto);
}
