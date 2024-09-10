package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByTitleContaining(String title);
    List<Book> findAllByCategory_Id (Long categoryId);

}
