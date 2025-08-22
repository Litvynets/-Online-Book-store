package mate.academy.bookstore.service.book.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.BookSearchParameters;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.repository.book.BookSpecificationBuilder;
import mate.academy.bookstore.repository.category.CategoryRepository;
import mate.academy.bookstore.service.book.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookRepository.save(bookMapper.toEntity(bookDto));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto createBookRequestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id));
        bookMapper.updateBookFromDto(createBookRequestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
        return bookMapper.toDtoList(bookRepository
                .findAll(bookSpecificationBuilder.build(searchParameters), pageable).toList());
    }

    @Override
    public List<BookDto> getAllByCategory(Long categoryId, Pageable pageable) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException("Can't find category by id: " + categoryId);
        }
        List<Book> books = bookRepository.findAllByCategories_Id(categoryId, pageable);
        return bookMapper.toDtoList(books);
    }
}
