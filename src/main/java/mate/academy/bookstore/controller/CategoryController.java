package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.service.book.BookService;
import mate.academy.bookstore.service.category.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Create a new category",
            description = "Create a new category and add it to the DB (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get all categories",
            description = "Get a list of categories from the DB")
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @Operation(
            summary = "Get a category by id",
            description = "Get a category by ID from the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(
                    responseCode = "404",
                    description = "A category with the specified ID doesn't exist")
    })
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Update category",
            description = "Update a category by ID in DB from the request body (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Updated the category fields and added changes to the DB"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "A category with the specified ID doesn't exist")
    })
    public CategoryDto updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CreateCategoryRequestDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a category by ID",
            description = "Delete a category from the DB by ID (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "The category has been successfully deleted from the DB"),
            @ApiResponse(responseCode = "404",
                    description = "A category with the specified ID doesn't exist")
    })
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @Operation(
            summary = "Get a books by category ID",
            description = "Get all available books from the DB by category ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "A category with the specified ID doesn't exist")
    })
    public List<BookDto> getBooksByCategoryId(@PathVariable Long id, Pageable pageable) {
        return bookService.getAllByCategory(id, pageable);
    }
}
