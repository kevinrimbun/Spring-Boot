package net.javaSpring.springBoot.service;

import net.javaSpring.springBoot.model.dto.BookDto;
import net.javaSpring.springBoot.model.dto.ResponseData;

public interface BookService {
    // Proses untuk CRUD
    ResponseData<Object> createBook(BookDto requesDto);

    ResponseData<Object> getAll();

    ResponseData<Object> getById(long id);
  
    ResponseData<Object> updateBook(long id, BookDto request);
  
    ResponseData<Object> deleteBook(long id);
}