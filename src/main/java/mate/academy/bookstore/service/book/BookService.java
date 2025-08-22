package mate.academy.bookstore.service.book;

import java.util.List;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.BookSearchParameters;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(long id);

    BookDto update(Long id, CreateBookRequestDto book);

    void delete(long id);

    List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable);

    List<BookDto> getAllByCategory(Long categoryId, Pageable pageable);
}
