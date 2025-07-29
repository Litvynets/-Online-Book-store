package mate.academy.bookstore.service.user.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.exception.RegistrationException;
import mate.academy.bookstore.mapper.user.UserMapper;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.role.RoleRepository;
import mate.academy.bookstore.repository.user.UserRepository;
import mate.academy.bookstore.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new RegistrationException("Email already exists");
        }
        User user = userMapper.registrationDtoToModel(registrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName(Role.RoleName.ROLE_USER).orElseThrow(() ->
                new EntityNotFoundException("Role "
                        + Role.RoleName.ROLE_USER.name()
                        + " not found in DB"));
        user.setRoles(Set.of(role));
        return userMapper.modelToResponseDto(userRepository.save(user));
    }
}
