package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.CategoryBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryBook, Long> {
}
