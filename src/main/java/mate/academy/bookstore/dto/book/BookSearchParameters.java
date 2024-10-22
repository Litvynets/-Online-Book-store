package mate.academy.bookstore.dto.book;

public record BookSearchParameters(String[] title, String[] author, String[] isbn) {
}
