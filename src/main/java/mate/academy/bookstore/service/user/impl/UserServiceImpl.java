package mate.academy.bookstore.service.user.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationException;
import mate.academy.bookstore.mapper.user.UserMapper;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.user.UserRepository;
import mate.academy.bookstore.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.email()).isPresent()) {
            throw new RegistrationException("Email already exists");
        }
        User user = userMapper.registrationDtoToModel(registrationDto);
        return userMapper.modelToResponseDto(userRepository.save(user));
    }
}
