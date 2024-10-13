package mate.academy.bookstore.mapper.user;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.user.UserRegistrationDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User registrationDtoToModel(UserRegistrationDto registrationDto);

    UserResponseDto modelToResponseDto(User user);
}
