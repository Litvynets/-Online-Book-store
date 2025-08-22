package mate.academy.bookstore.service.category.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.category.CategoryMapper;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.category.CategoryRepository;
import mate.academy.bookstore.service.category.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryMapper.toDtoList(categoryRepository.findAll(pageable).getContent());
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find Category with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto createCategoryRequestDtoDto) {
        Category category = categoryMapper.toEntity(createCategoryRequestDtoDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDtoDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find Category with id: " + id)
        );
        categoryMapper.updateCategory(category, categoryRequestDtoDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Can't find Category with id: " + id);
        }
    }
}
