package mate.academy.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto extends CreateBookRequestDto {
    private Long id;
}
