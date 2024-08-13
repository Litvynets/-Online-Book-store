package mate.academy.bookstore.dto;

public record BookSearchParameters(String[] title, String[] author, String[] isbn) {
}
