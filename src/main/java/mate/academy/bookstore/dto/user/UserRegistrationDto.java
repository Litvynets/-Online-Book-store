package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.NotBlank;
import mate.academy.bookstore.validation.Email;
import mate.academy.bookstore.validation.FieldMatch;
import org.hibernate.validator.constraints.Length;

@FieldMatch(firstField = "password",
        secondField = "repeatPassword",
        message = "Password do not match")
public record UserRegistrationDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 8, max = 64)
        String password,
        @NotBlank
        @Length(min = 8, max = 64)
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress) {
}
