package mate.academy.bookstore.repository.book;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookSearchParameters;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationBuilder;
import mate.academy.bookstore.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> bookSpec = Specification.where(null);
        if (searchParameters.author() != null && searchParameters.author().length > 0) {
            bookSpec = bookSpec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("author")
                    .getSpecification(searchParameters.author()));
        }
        if (searchParameters.isbn() != null && searchParameters.isbn().length > 0) {
            bookSpec = bookSpec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("isbn")
                    .getSpecification(searchParameters.isbn()));
        }
        if (searchParameters.title() != null && searchParameters.title().length > 0) {
            bookSpec = bookSpec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("title")
                    .getSpecification(searchParameters.title()));
        }
        return bookSpec;
    }
}
