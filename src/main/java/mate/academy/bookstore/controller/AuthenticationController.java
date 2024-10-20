package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication controller", description = "endpoints for authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    @Operation(summary = "User registration",
            description = "Registration of a new user in the bookstore")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The user is successfully registered"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid request body")
    })
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return userService.register(userRegistrationDto);
    }
}
