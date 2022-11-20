package net.javaSpring.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


import net.javaSpring.springBoot.model.dto.BookDto;
import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.model.entity.Book;
import net.javaSpring.springBoot.model.entity.Category;
import net.javaSpring.springBoot.repository.BookRepository;
import net.javaSpring.springBoot.repository.CategoryRepository;


@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
  
    private ResponseData<Object> responseData;
    private Book book;
    private List<Book> books;
    private Category category;
  
    @Override
    public ResponseData<Object> createBook(BookDto requesDto) {
      // TODO Auto-generated method stub
      book = new Book(requesDto.getTitle(), requesDto.getAuthor());

      category = categoryRepository.findByName(requesDto.getCategoryName());
      book.setCategory(category);

  
      // save to db
      bookRepository.save(book);
  
      // response data
      responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "add successfully", book);
      return responseData;
    }
  
    @Override
    public ResponseData<Object> getAll() {
      // TODO Auto-generated method stub
      // find all book
      books = bookRepository.findAll();
  
      // response data
      responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", books);
      return responseData;
    }
  
    @Override
    public ResponseData<Object> getById(long id) {
      // TODO Auto-generated method stub
      Optional<Book> bookOpt = bookRepository.findById(id);
      if (bookOpt.isPresent()) {
        book = bookOpt.get();
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", book);
      } else {
        responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
      }
      return responseData;
    }
  
    @Override
    public ResponseData<Object> updateBook(long id, BookDto request) {
      // TODO Auto-generated method stub
      Optional<Book> bookOpt = bookRepository.findById(id);
      if (bookOpt.isPresent()) {
        book = bookOpt.get();
  
        // update buku
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());

        category = categoryRepository.findByName(request.getCategoryName());
        book.setCategory(category);
  
        // save
        bookRepository.save(book);
  
        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "updated success", book);
      } else {
        responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
      }
      return responseData;
    }
  
    @Override
    public ResponseData<Object> deleteBook(long id) {
      // TODO Auto-generated method stub
      Optional<Book> bookOpt = bookRepository.findById(id);
      if (bookOpt.isPresent()) {
        book = bookOpt.get();
  
        // delete / change property isDeleted
        book.setDeleted(true);
  
        // save
        bookRepository.save(book);
  
        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", null);
      } else {
        responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
      }
      return responseData;
    }
}