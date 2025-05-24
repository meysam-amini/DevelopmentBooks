package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.adapters.book.out.persistence.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    boolean existsByIsbn(String isbn);
}
