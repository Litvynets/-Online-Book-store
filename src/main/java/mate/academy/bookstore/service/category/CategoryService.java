package mate.academy.bookstore.service.category;

import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryRequestDto createCategoryRequestDtoDto);

    CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDtoDto);

    void deleteById(Long id);
}
