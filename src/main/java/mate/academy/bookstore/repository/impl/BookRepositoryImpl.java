package mate.academy.bookstore.repository.impl;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save book: " + book + " to the DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> fromBook = session.createQuery("FROM Book", Book.class);
            return fromBook.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all books from DB", e);
        }
    }
}
