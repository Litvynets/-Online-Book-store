package mate.academy.bookstore.mapper.category;

import java.util.List;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequestDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categoryList);

    void updateCategory(@MappingTarget Category category, CreateCategoryRequestDto categoryDto);
}
